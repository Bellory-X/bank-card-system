package com.bank.management.account.impl.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.management.account.impl.model.TransferAccount;

public interface TransferAccountRepository extends JpaRepository<TransferAccount, UUID> {

    @Query("select t from TransferAccount where t.completed = false order by t.guid")
    public List<TransferAccount> findAllComplitedIsFalse();

    @Query("select t from TransferAccount where t.completed = true order by t.guid")
    public List<TransferAccount> findAllComplitedIsTrue();
}
