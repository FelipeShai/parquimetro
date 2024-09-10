package br.com.fiap.parquimetro.domain.dtos;

import java.util.UUID;

public record ParkingSpaceDTO(
        UUID id,
        boolean occupied,
        String location,
        VehicleDTO vehicle
) {}
