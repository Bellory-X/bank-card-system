package com.bank.management.transfer.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransferSheduler {

    private final TransferCommands commands;

    @Scheduled(fixedRate = 300000)
    void sendTransfers() {
        commands.send();
    }
}
