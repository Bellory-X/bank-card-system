package com.bank.management.user.exception;

import com.bank.management.util.DomainException;

public class NotFoundUserException extends DomainException {

    public NotFoundUserException() {
        super("Пользователь не найден.");
    }
}
