package com.bank.management.card.exception;

import java.util.UUID;

import com.bank.management.util.DomainException;

public class NotFoundCardException extends DomainException {

    public NotFoundCardException(UUID cardId) {
        super("Банковская карта с id = %s не найдена.");
    }
}
