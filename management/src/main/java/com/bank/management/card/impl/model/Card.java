package com.bank.management.card.impl.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.bank.manager.card.api.model.CardStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    private UUID guid;

    @Column(name = "encrypted_number", nullable = false, unique = true, length = 255)
    private String encryptedNumber;

    @Column(name = "masked_number", nullable = false, length = 50)
    private String maskedNumber;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "cardholder_name", nullable = false, length = 100)
    private String cardholderName;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CardStatus status;

    @Column(name = "account_guid", nullable = false)
    private UUID accountGuid;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "RUB";

    @Column(name = "user_guid", nullable = false)
    private UUID userGuid;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
