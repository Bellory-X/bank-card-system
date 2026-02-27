package com.bank.management.account.api;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record TransferAccontEvent(
    UUID guid,
    UUID fromAccountGuid,
    UUID toAccountGuid,
    BigDecimal amount
) {

    public TransferAccontEvent {
        Objects.requireNonNull(guid);
        Objects.requireNonNull(fromAccountGuid);
        Objects.requireNonNull(toAccountGuid);
        Objects.requireNonNull(amount);
    }
}
