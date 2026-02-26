package com.bank.management.account.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountSheduler {

    private final AccountCommands commands;
    
    @Scheduled(fixedRate = 300000)
    void transfer() {
        commands.transfer();
    }

    @Scheduled(fixedRate = 300000)
    void sendCompletedTransfer() {
        commands.sendCompletedTransfers();
    }
}
