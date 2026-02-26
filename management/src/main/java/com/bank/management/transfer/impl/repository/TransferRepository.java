package com.bank.management.transfer.impl.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bank.management.transfer.impl.model.Transfer;

public interface TransferRepository extends 
    JpaRepository<Transfer, UUID>,
    JpaSpecificationExecutor<Transfer> {
        
        public default Transfer getByGuid(UUID transferGuid) {
            return findById(transferGuid).orElseThrow();
        }
}
