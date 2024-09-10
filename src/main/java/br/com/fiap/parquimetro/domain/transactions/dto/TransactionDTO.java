package br.com.fiap.parquimetro.domain.transactions.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        UUID vehicleId,
        UUID parkingSpaceId,
        LocalDateTime entryTime,
        LocalDateTime exitTime,
        BigDecimal amountCharged
) {}
