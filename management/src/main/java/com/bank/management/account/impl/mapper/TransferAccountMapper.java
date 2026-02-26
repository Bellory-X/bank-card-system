package com.bank.management.account.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.management.account.api.TransferAccontEvent;
import com.bank.management.account.impl.model.TransferAccount;
import com.bank.management.card.api.CompleteAccountTransferEvent;

@Mapper(componentModel = "spring")
public interface TransferAccountMapper {

    @Mapping(target = "completed", ignore = true)
    @Mapping(target = "description", ignore = true)
    public TransferAccount toTransferAccount(TransferAccontEvent event);

    public CompleteAccountTransferEvent toCompleteAccountTransferEvent(TransferAccount transferAccount);
}
