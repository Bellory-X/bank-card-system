package com.bank.management.card.impl.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.bank.management.card.impl.model.Card;
import com.bank.manager.card.api.model.CardStatus;

import jakarta.persistence.criteria.Predicate;

public class CardSpecification {

    private CardSpecification() {}

    public static Specification<Card> byUserGuid(UUID userGuid) {
        return (root, query, cb) -> cb.equal(root.get("userGuid"), userGuid);
    }

    public static Specification<Card> byStatus(CardStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public static Specification<Card> byCardholderNameLike(String name) {
        return (root, query, cb) -> cb.like(
            cb.lower(root.get("cardholderName")),
            "%" + name.toLowerCase() + "%"
        );
    }

    public static Specification<Card> byExpiryDateBefore(LocalDate date) {
        return (root, query, cb) -> cb.lessThan(root.get("expiryDate"), date);
    }

    public static Specification<Card> byExpiryDateAfter(LocalDate date) {
        return (root, query, cb) -> cb.greaterThan(root.get("expiryDate"), date);
    }

    public static Specification<Card> byExpiryDateBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> cb.between(root.get("expiryDate"), from, to);
    }

    public static Specification<Card> byBalanceGreaterThan(BigDecimal balance) {
        return (root, query, cb) -> cb.greaterThan(root.get("balance"), balance);
    }

    public static Specification<Card> byBalanceLessThan(BigDecimal balance) {
        return (root, query, cb) -> cb.lessThan(root.get("balance"), balance);
    }

    public static Specification<Card> byBalanceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> cb.between(root.get("balance"), min, max);
    }

    public static Specification<Card> byCurrency(String currency) {
        return (root, query, cb) -> cb.equal(root.get("currency"), currency);
    }

    public static Specification<Card> createdAfter(LocalDateTime date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<Card> createdBefore(LocalDateTime date) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), date);
    }

    public static Specification<Card> byLastTransactionGuid(UUID transactionGuid) {
        return (root, query, cb) -> cb.equal(root.get("lastTransactionGuid"), transactionGuid);
    }

    public static Specification<Card> withFilters(
            UUID userGuid,
            CardStatus status,
            String cardholderNameFilter,
            String currency,
            LocalDate expiryDateFrom,
            LocalDate expiryDateTo,
            BigDecimal minBalance,
            BigDecimal maxBalance
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userGuid != null) {
                predicates.add(cb.equal(root.get("userGuid"), userGuid));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (cardholderNameFilter != null && !cardholderNameFilter.trim().isEmpty()) {
                predicates.add(cb.like(
                    cb.lower(root.get("cardholderName")),
                    "%" + cardholderNameFilter.toLowerCase() + "%"
                ));
            }

            if (currency != null && !currency.trim().isEmpty()) {
                predicates.add(cb.equal(root.get("currency"), currency));
            }

            if (expiryDateFrom != null && expiryDateTo != null) {
                predicates.add(cb.between(root.get("expiryDate"), expiryDateFrom, expiryDateTo));
            } else if (expiryDateFrom != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("expiryDate"), expiryDateFrom));
            } else if (expiryDateTo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("expiryDate"), expiryDateTo));
            }

            if (minBalance != null && maxBalance != null) {
                predicates.add(cb.between(root.get("balance"), minBalance, maxBalance));
            } else if (minBalance != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("balance"), minBalance));
            } else if (maxBalance != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("balance"), maxBalance));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
