package com.bank.management.card.impl.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bank.management.card.exception.NotFoundCardException;
import com.bank.management.card.impl.model.Card;

@Repository
public interface CardRepository extends 
        JpaRepository<Card, UUID>, 
        JpaSpecificationExecutor<Card> {

    public default Card getByCardGuid(UUID cardGuid) {
        return findById(cardGuid).orElseThrow(() -> new NotFoundCardException(cardGuid));
    }

    Optional<Card> findByIdAndUserGuid(UUID id, UUID userGuid);

    Page<Card> findByUserGuid(UUID userGuid, Pageable pageable);

    boolean existsByIdAndUserGuid(UUID id, UUID userGuid);

    boolean existsByEncryptedNumber(String encryptedNumber);
}
