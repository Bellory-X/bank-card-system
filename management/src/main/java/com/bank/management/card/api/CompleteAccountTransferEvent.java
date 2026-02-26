package com.bank.management.card.api;

import java.util.UUID;

public record CompleteAccountTransferEvent(UUID guid, String description, boolean completed) {
}
