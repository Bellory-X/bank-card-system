package com.bank.management.card.api;

import com.bank.manager.card.api.model.CardSortField;
import com.bank.manager.card.api.model.CardStatus;
import com.bank.manager.card.api.model.SortDirection;

public record ProfileCardFilter(
    Integer page,
    Integer size,
    CardSortField sortBy,
    SortDirection sortDirection,
    CardStatus status,
    String cardholderNameFilter
) {
}
