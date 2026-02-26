// card/impl/mapper/CardMapper.java
package com.bank.management.card.impl.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.bank.management.card.impl.model.Card;
import com.bank.manager.card.api.model.AdminCardPageable;
import com.bank.manager.card.api.model.AdminCardResponse;
import com.bank.manager.card.api.model.TransferResponse;
import com.bank.manager.card.api.model.UserCardPageable;
import com.bank.manager.card.api.model.UserCardResponse;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maskedNumber", target = "maskedNumber")
    @Mapping(source = "cardholderName", target = "cardholderName")
    @Mapping(source = "expiryDate", target = "expiryDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "currency", target = "currency")
    UserCardResponse toUserCardResponse(Card card);

    List<UserCardResponse> toUserCardResponseList(List<Card> cards);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "maskedNumber", target = "maskedNumber")
    @Mapping(source = "encryptedNumber", target = "encryptedNumber")
    @Mapping(source = "cardholderName", target = "cardholderName")
    @Mapping(source = "expiryDate", target = "expiryDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userGuid", target = "userId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "lastTransactionGuid", target = "lastTransactionId")
    @Mapping(source = "currency", target = "currency")
    AdminCardResponse toAdminCardResponse(Card card);

    List<AdminCardResponse> toAdminCardResponseList(List<Card> cards);

    default UserCardPageable toUserCardPageable(Page<Card> page) {
        UserCardPageable pageable = new UserCardPageable();
        pageable.setContent(toUserCardResponseList(page.getContent()));
        pageable.setPage(page.getNumber());
        pageable.setSize(page.getSize());
        pageable.setTotalElements(page.getTotalElements());
        pageable.setTotalPages(page.getTotalPages());
        pageable.setFirst(page.isFirst());
        pageable.setLast(page.isLast());
        return pageable;
    }

    default AdminCardPageable toAdminCardPageable(Page<Card> page) {
        AdminCardPageable pageable = new AdminCardPageable();
        pageable.setContent(toAdminCardResponseList(page.getContent()));
        pageable.setPage(page.getNumber());
        pageable.setSize(page.getSize());
        pageable.setTotalElements(page.getTotalElements());
        pageable.setTotalPages(page.getTotalPages());
        pageable.setFirst(page.isFirst());
        pageable.setLast(page.isLast());
        return pageable;
    }

    @Named("mapToTransferResponse")
    default TransferResponse mapToTransferResponse(
            UUID transactionGuid,
            UUID fromCardId,
            UUID toCardId,
            BigDecimal amount,
            BigDecimal newBalance,
            String description
    ) {
        TransferResponse response = new TransferResponse();
        response.setTransactionId(transactionGuid);
        response.setFromCardId(fromCardId);
        response.setToCardId(toCardId);
        // response.setAmount(amount);
        // response.setNewBalance(newBalance);
        // response.setTimestamp(LocalDateTime.now());
        response.setDescription(description);
        return response;
    }
}