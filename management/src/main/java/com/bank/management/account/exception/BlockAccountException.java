package com.bank.management.account.exception;

import com.bank.management.util.DomainException;

public class BlockAccountException extends DomainException {

    public BlockAccountException() {
        super("Невозможно выполнить операцию, счет заблокирован.");
    }
}
