package com.bank.management.account.impl;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.bank.management.account.api.BlockUserAccontEvent;
import com.bank.management.account.api.TransferAccontEvent;
import com.bank.management.account.api.UnblockUserAccountEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AccountListener {

    private final AccountCommands commands;

    @EventListener(BlockUserAccontEvent.class)
    void handle(BlockUserAccontEvent event) {
        commands.block(event);
    }

    @EventListener(UnblockUserAccountEvent.class)
    void handle(UnblockUserAccountEvent event) {
        commands.unblock(event);
    }

    @EventListener(TransferAccontEvent.class)
    void handle(TransferAccontEvent event) {
        commands.saveTransfer(event);
    }
}
