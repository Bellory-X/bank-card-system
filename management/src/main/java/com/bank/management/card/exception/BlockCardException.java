package com.bank.management.card.exception;

import com.bank.management.util.DomainException;

public class BlockCardException extends DomainException {

    public BlockCardException() {
        super("Не возможно посмотреть баланс неактивированной карты.");
    }
}
