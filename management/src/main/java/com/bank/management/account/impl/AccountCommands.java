package com.bank.management.account.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.account.api.BlockUserAccontEvent;
import com.bank.management.account.api.TransferAccontEvent;
import com.bank.management.account.api.UnblockUserAccountEvent;
import com.bank.management.account.exception.BlockAccountException;
import com.bank.management.account.impl.mapper.TransferAccountMapper;
import com.bank.management.account.impl.model.Account;
import com.bank.management.account.impl.model.TransferAccount;
import com.bank.management.account.impl.repository.AccountRepository;
import com.bank.management.account.impl.repository.TransferAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountCommands {

    private final AccountRepository accountRepository;
    private final TransferAccountRepository transferAccountRepository;
    private final TransferAccountMapper transferAccountMapper;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void block(BlockUserAccontEvent event) {
        List<Account> accounts = accountRepository.getAllByUserGuid(event.userGuid());
        accounts.forEach(it -> it.setEnabled(false));
        accountRepository.saveAll(accounts);
    }

    @Transactional
    public void unblock(UnblockUserAccountEvent event) {
        List<Account> accounts = accountRepository.getAllByUserGuid(event.userGuid());
        accounts.forEach(it -> it.setEnabled(true));
        accountRepository.saveAll(accounts);
    }

    @Transactional
    public BigDecimal getBalance(UUID accountGuid) {
        Account account = accountRepository.getByAccountGuid(accountGuid);
        if (!account.isEnabled()) {
            throw new BlockAccountException();
        }
        return account.getBalance();
    }

    @Transactional
    public void saveTransfer(TransferAccontEvent event) {
        if (transferAccountRepository.existsById(event.guid())) {
            return;
        }
        TransferAccount transferAccount = transferAccountMapper.toTransferAccount(event);
        transferAccountRepository.save(transferAccount);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void transfer() {
        List<TransferAccount> transferAccounts = transferAccountRepository.findAllComplitedIsFalse();
        for (var transferAccount : transferAccounts) {
            Account fromAccount = accountRepository.getByAccountGuid(transferAccount.getFromAccountGuid());
            Account toAccount = accountRepository.getByAccountGuid(transferAccount.getToAccountGuid());
            if (!fromAccount.isEnabled()) {
                transferAccount.setDescription("Невозможно выполнить операцию, счет отправителя заблокирован.");

            } else if (transferAccount.getPrice().compareTo(fromAccount.getBalance()) > 0) {
                transferAccount.setDescription("Недостаточно средств для совершения перевода.");
            } else {
                fromAccount.setBalance(fromAccount.getBalance().subtract(transferAccount.getPrice()));
                toAccount.setBalance(toAccount.getBalance().add(transferAccount.getPrice()));
                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);
                transferAccount.setDescription("Операция выполнена успешно.");
            }
            transferAccount.setCompleted(true);
            transferAccountRepository.save(transferAccount);
        }
    }

    @Transactional
    public void sendCompletedTransfers() {
        List<TransferAccount> transferAccounts = transferAccountRepository.findAllComplitedIsTrue();
        transferAccountRepository.deleteAll(transferAccounts);
        transferAccounts.stream()
            .map(transferAccountMapper::toCompleteAccountTransferEvent)
            .forEach(publisher::publishEvent);
    }
}
