package com.bank.management.transfer.api;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record AddTransferResponse(
    UUID guid,
    UUID toAccountGuid,
    UUID fromAccountGuid,
    BigDecimal price,
    ZonedDateTime createdAt,
    Boolean completed,
    String description
) {

}
