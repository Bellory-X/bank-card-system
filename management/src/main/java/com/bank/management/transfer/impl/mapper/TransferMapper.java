package com.bank.management.transfer.impl.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.bank.management.account.api.TransferAccontEvent;
import com.bank.management.card.api.CompleteAccountTransferEvent;
import com.bank.management.transfer.api.AddTransferRequest;
import com.bank.management.transfer.api.AddTransferResponse;
import com.bank.management.transfer.impl.model.AccountTransfer;
import com.bank.management.transfer.impl.model.Transfer;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "price", ignore = true)
    @Mapping(target = "toAccountGuid", ignore = true)
    @Mapping(target = "fromAccountGuid", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public void changeTransfer(
        @MappingTarget Transfer transfer, 
        CompleteAccountTransferEvent event
    );

    @Mapping(target = "guid", ignore = true)
    @Mapping(target = "completed", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "description", ignore = true)
    public Transfer toTransfer(AddTransferRequest request);

    public TransferAccontEvent toTransferAccontEvent(AccountTransfer accountTransfer);


    @Mapping(target = "send", ignore = true)
    public AccountTransfer toAccountTransfer(Transfer transfer);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "toZonedDateTime")
    public AddTransferResponse toAddTransferResponse(Transfer transfer);

    @Named("toZonedDateTime")
    default ZonedDateTime localDateTimeToZonedDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault());
    }
}
