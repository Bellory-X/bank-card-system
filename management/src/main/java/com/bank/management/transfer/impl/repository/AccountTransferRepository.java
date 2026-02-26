package com.bank.management.transfer.impl.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.management.transfer.impl.model.AccountTransfer;

@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransfer, UUID> {

    @Query("select t from AccountTransfer order by guid")
    public List<AccountTransfer> findAllOrderByGuid();
}
