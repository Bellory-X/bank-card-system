package com.bank.management.transfer.api;

import java.util.UUID;

import org.springframework.stereotype.Component;
import com.bank.management.transfer.impl.TransferCommands;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransferAdapter {

    private final TransferCommands commands;

    public AddTransferResponse add(AddTransferRequest request) {
        return commands.add(request);
    }

    public AddTransferResponse get(UUID transferGuid) {
        return commands.get(transferGuid);
    }

    public TransferPageable get(TransferFilter filter) {
        return commands.get(filter);
    }
}
