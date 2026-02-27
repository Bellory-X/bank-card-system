package com.bank.management.account.api;

import java.util.Objects;
import java.util.UUID;

public record BlockUserAccontEvent(UUID userGuid) {

    public BlockUserAccontEvent {
        Objects.requireNonNull(userGuid);
    }

}
