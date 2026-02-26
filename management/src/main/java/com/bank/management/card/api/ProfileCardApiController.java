package com.bank.management.card.api;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.management.card.impl.CardCommands;
import com.bank.management.security.JwtUtils;
import com.bank.manager.card.api.UserCardApi;
import com.bank.manager.card.api.model.CardSortField;
import com.bank.manager.card.api.model.CardStatus;
import com.bank.manager.card.api.model.SortDirection;
import com.bank.manager.card.api.model.TransferRequest;
import com.bank.manager.card.api.model.TransferResponse;
import com.bank.manager.card.api.model.UserCardPageable;
import com.bank.manager.card.api.model.UserCardResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class ProfileCardApiController implements UserCardApi {
    
    private final CardCommands commands;
    private final JwtUtils jwtUtils;

    @Override
    public UserCardResponse getUserCardById(UUID cardGuid) {
        return commands.get(cardGuid);
    }

    @Override
    public UserCardPageable getUserCards(
        @Min(0) @Valid Integer page,
        @Min(1) @Max(100) @Valid Integer size,
        @Valid CardSortField sortBy,
        @Valid SortDirection sortDirection,
        @Valid CardStatus status,
        @Size(min = 2) @Valid String cardholderNameFilter
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserCards'");
    }

    @Override
    public void requestCardBlock(UUID cardGuid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestCardBlock'");
    }

    @Override
    public TransferResponse transferBetweenOwnCards(
        @Valid TransferRequest transferRequest
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferBetweenOwnCards'");
    }

    public BigDecimal getBalance(UUID cardGuid) {
        return commands.getBalance(cardGuid);
    }
}
