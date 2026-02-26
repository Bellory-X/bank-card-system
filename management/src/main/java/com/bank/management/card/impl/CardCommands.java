// card/impl/service/CardServiceImpl.java
package com.bank.management.card.impl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.management.account.api.AccontAdapter;
import com.bank.management.account.exception.NotFoundAccountException;
import com.bank.management.card.exception.BlockCardException;
import com.bank.management.card.impl.mapper.CardMapper;
import com.bank.management.card.impl.model.Card;
import com.bank.management.card.impl.repository.CardRepository;
import com.bank.manager.card.api.model.CardStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardCommands {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardNumberGenerator cardNumberGenerator;
    private final AccontAdapter accontAdapter;

    @Transactional
    public BigDecimal getBalance(UUID cardGuid) {
        Card card = cardRepository.getByCardGuid(cardGuid);
        if (Objects.equals(CardStatus.ACTIVE, card.getStatus())) {
            throw new BlockCardException();
        }
        try {
            return accontAdapter.getBalance(card.getAccountGuid());
        } catch (NotFoundAccountException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
    }
}