package com.bank.management.account.exception;

import com.bank.management.util.DomainException;

public class NotFoundAccountException extends DomainException {

    public NotFoundAccountException() {
        super("Счет не найден.");
    }
}
