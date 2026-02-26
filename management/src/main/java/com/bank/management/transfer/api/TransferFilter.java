package com.bank.management.transfer.api;

import java.time.ZonedDateTime;

public record TransferFilter(
    ZonedDateTime createdAt,
    Boolean completed,
    String description,
    Integer pageNumber,
    Integer pageSize,
    TransferSortField sortField,
    SortDirection sortDirection
) {
}
