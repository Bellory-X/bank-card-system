package com.bank.management.account.impl.model;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

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
public class Account {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID guid;

    @Column(name = "user_guid", nullable = false)
    private UUID userGuid;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
}
