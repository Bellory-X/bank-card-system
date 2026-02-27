package com.bank.management.account.api;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Component;
import com.bank.management.account.impl.AccountCommands;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccontAdapter {

    private final AccountCommands commands;

    public BigDecimal getBalance(UUID accountGuid) {
        return commands.getBalance(accountGuid);
    }
}
