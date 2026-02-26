package com.bank.management.card.api;

import org.springframework.web.bind.annotation.RestController;

import com.bank.management.card.impl.CardCommands;
import com.bank.management.security.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CardApiController {

    private final CardCommands commands;
    private final JwtUtils jwtUtils;

}
