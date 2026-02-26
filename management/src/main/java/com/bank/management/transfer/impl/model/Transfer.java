package com.bank.management.transfer.impl.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    @Column(name = "guid")
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID guid;

    @Column(name = "to_account_guid", nullable = false)
    private UUID toAccountGuid;

    @Column(name = "from_account_guid", nullable = false)
    private UUID fromAccountGuid;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = ZonedDateTime.now().toLocalDateTime();

    @Column(name = "completed", updatable = false)
    private boolean completed = false;

    @Column(name = "description", updatable = false)
    private String description = "";
}
