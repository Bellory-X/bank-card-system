package com.bank.management.transfer.api;

import java.math.BigDecimal;
import java.util.UUID;

public record AddTransferRequest(
    UUID toAccountGuid, 
    UUID fromAccountGuid, 
    BigDecimal amount
) {
}
