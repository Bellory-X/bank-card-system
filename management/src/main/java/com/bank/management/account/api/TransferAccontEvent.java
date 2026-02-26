package com.bank.management.account.api;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferAccontEvent(
    UUID guid,
    UUID fromAccountGuid,
    UUID toAccountGuid,
    BigDecimal price
) {
}
