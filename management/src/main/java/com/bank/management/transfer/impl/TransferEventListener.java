package com.bank.management.transfer.impl;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bank.management.card.api.CompleteAccountTransferEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransferEventListener {

    private final TransferCommands commands;

    @EventListener(CompleteAccountTransferEvent.class)
    void handle(CompleteAccountTransferEvent event) {
        commands.complete(event);
    }
}
