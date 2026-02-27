package com.bank.management.account.api;

import java.util.Objects;
import java.util.UUID;

public record UnblockUserAccountEvent(UUID userGuid) {

    public UnblockUserAccountEvent {
        Objects.requireNonNull(userGuid);
    }
}
