package com.bank.management.account.impl.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferAccount {

    @Id
    @Column(name = "guid")
    private UUID guid;

    @Column(name = "to_account_guid", nullable = false)
    private UUID toAccountGuid;

    @Column(name = "from_account_guid", nullable = false)
    private UUID fromAccountGuid;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    @Column(name = "description", nullable = false)
    private String description = "";
}
