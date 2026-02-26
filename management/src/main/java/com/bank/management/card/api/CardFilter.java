package com.bank.management.card.api;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bank.manager.card.api.model.CardSortField;
import com.bank.manager.card.api.model.CardStatus;
import com.bank.manager.card.api.model.SortDirection;

public record CardFilter(
    Integer page, 
    Integer size,
    CardSortField sortBy, 
    SortDirection sortDirection, 
    CardStatus status,
    UUID userId, 
    String cardholderNameFilter
) {
    public Pageable toPageable() {
        Sort sort = Sort.by(
            Sort.Direction.fromString(sortDirection.name()),
            mapSortField(sortBy)
        );
        return PageRequest.of(page, size, sort);
    }

    private String mapSortField(CardSortField field) {
        if (field == null) return "createdAt";
        
        return switch (field) {
            case CARD_NUMBER -> "encryptedNumber";
            case CARDHOLDER_NAME -> "cardholderName";
            case EXPIRY_DATE -> "expiryDate";
            case STATUS -> "status";
            case BALANCE -> "balance";
            case CREATED_AT -> "createdAt";
            default -> "createdAt";
        };
    }
}
