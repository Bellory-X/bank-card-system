package com.bank.management.transfer.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.card.api.CompleteAccountTransferEvent;
import com.bank.management.transfer.api.AddTransferRequest;
import com.bank.management.transfer.api.AddTransferResponse;
import com.bank.management.transfer.api.TransferFilter;
import com.bank.management.transfer.api.TransferPageable;
import com.bank.management.transfer.impl.mapper.TransferMapper;
import com.bank.management.transfer.impl.model.AccountTransfer;
import com.bank.management.transfer.impl.model.Transfer;
import com.bank.management.transfer.impl.repository.AccountTransferRepository;
import com.bank.management.transfer.impl.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferCommands {

    private final AccountTransferRepository accountTransferRepository;
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void complete(CompleteAccountTransferEvent event) {
        Transfer transfer = transferRepository.getByGuid(event.guid());
        if (transfer.isCompleted()) {
            return;
        }
        transferMapper.changeTransfer(transfer, event);
        transferRepository.save(transfer);
    }

    @Transactional
    public void send() {
        List<AccountTransfer> accountTransfers = accountTransferRepository.findAllOrderByGuid();
        accountTransferRepository.deleteAll(accountTransfers);
        accountTransfers.stream()
            .map(transferMapper::toTransferAccontEvent)
            .forEach(publisher::publishEvent);
    }

    @Transactional
    public AddTransferResponse add(AddTransferRequest request) {
        Transfer transfer = transferMapper.toTransfer(request);
        transferRepository.save(transfer);
        AccountTransfer accountTransfer = transferMapper.toAccountTransfer(transfer);
        accountTransferRepository.save(accountTransfer);
        return transferMapper.toAddTransferResponse(transfer);
    }

    @Transactional
    public AddTransferResponse get(UUID transferGuid) {
        Transfer transfer = transferRepository.getByGuid(transferGuid);
        return transferMapper.toAddTransferResponse(transfer);
    }

    @Transactional
    public TransferPageable get(TransferFilter filter) {
        return null;
    }
}
