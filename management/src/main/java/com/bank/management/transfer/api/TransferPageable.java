package com.bank.management.transfer.api;

import java.util.List;

public record TransferPageable(
    List<AddTransferResponse> content,
    Integer page,
    Integer size,
    Long totalElements,
    Integer totalPages
) {

}
