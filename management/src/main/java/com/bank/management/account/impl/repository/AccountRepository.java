package com.bank.management.account.impl.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.management.account.exception.NotFoundAccountException;
import com.bank.management.account.impl.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{

    public default Account getByAccountGuid(UUID accountGuid) {
        return findById(accountGuid).orElseThrow(() -> new NotFoundAccountException());
    }

    public List<Account> getAllByUserGuid(UUID userGuid);
}
