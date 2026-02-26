package com.bank.management.card.impl;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
class CardNumberGenerator {

    private static final Random RANDOM = new SecureRandom();
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final String BIN = "400000"; // Банковский идентификатор

    String generateCardNumber() {
        StringBuilder sb = new StringBuilder(BIN);
        
        for (int i = 0; i < CARD_NUMBER_LENGTH - BIN.length() - 1; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        
        // Добавляем контрольную цифру (Luhn algorithm)
        sb.append(calculateLuhnCheckDigit(sb.toString()));
        
        return sb.toString();
    }

    String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    String encryptCardNumber(String cardNumber) {
        // В реальном приложении здесь должно быть настоящее шифрование
        // Например, через AES или другую библиотеку
        return Base64.getEncoder().encodeToString(cardNumber.getBytes());
    }

    private int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;
        
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        
        return (sum * 9) % 10;
    }
}