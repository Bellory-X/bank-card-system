package com.bank.management.transfer.impl.model;

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
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransfer {

    @Id
    private UUID guid;

    @Column(name = "from_card_guid", nullable = false)
    private UUID fromAccountGuid;

    @Column(name = "to_card_guid", nullable = false)
    private UUID toAccountGuid;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "send", nullable = false)
    private boolean send = false;
}
