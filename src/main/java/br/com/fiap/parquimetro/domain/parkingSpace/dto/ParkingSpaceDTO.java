package br.com.fiap.parquimetro.domain.parkingSpace.dto;

import br.com.fiap.parquimetro.domain.vehicle.dto.VehicleDTO;

import java.util.UUID;

public record ParkingSpaceDTO(
        UUID id,
        boolean occupied,
        String location,
        VehicleDTO vehicle
) {}
