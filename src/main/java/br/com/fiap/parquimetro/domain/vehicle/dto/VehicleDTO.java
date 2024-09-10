package br.com.fiap.parquimetro.domain.vehicle.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleDTO(

        UUID id,

        String licensePlate,

        String carModel,

        LocalDateTime entryTime
) {
}
