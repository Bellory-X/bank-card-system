package com.bank.management.user.exception;

import com.bank.management.util.DomainException;

public class InvalidPasswordProfileException extends DomainException {

    public InvalidPasswordProfileException() {
        super("Не верный текущий пароль.");
    }
}
