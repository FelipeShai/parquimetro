package br.com.fiap.parquimetro.domain.parkingSpace.service;

import br.com.fiap.parquimetro.domain.parkingSpace.dto.ParkingSpaceDTO;
import br.com.fiap.parquimetro.domain.parkingSpace.entity.ParkingSpace;
import br.com.fiap.parquimetro.domain.parkingSpace.repository.ParkingSpaceRepository;
import br.com.fiap.parquimetro.domain.vehicle.dto.VehicleDTO;
import br.com.fiap.parquimetro.domain.vehicle.entity.Vehicle;
import br.com.fiap.parquimetro.domain.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingSpaceService {

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private VehicleService vehicleService;


    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace newSpace = toEntity(parkingSpaceDTO);
        return toDTO(parkingSpaceRepository.save(newSpace));
    }

    public List<ParkingSpaceDTO> createMultipleParkingSpaces(int numberOfSpaces, String section) {
        List<ParkingSpace> spaces = new ArrayList<>();
        for (int i = 1; i <= numberOfSpaces; i++) {
            String location = section + " - Spot " + i;
            ParkingSpace space = new ParkingSpace(false, location);
            spaces.add(space);
        }
        List<ParkingSpace> savedSpaces = parkingSpaceRepository.saveAll(spaces);
        return savedSpaces.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ParkingSpaceDTO> getAllSpaces() {
        return parkingSpaceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ParkingSpaceDTO occupySpace(UUID id, UUID vehicleId) {
        ParkingSpace space = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));
        if (space.isOccupied()) {
            throw new RuntimeException("Parking space is already occupied");
        }

        Vehicle vehicle = vehicleService.toVehicle(vehicleService.getVehicleById(vehicleId));

        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        space.setOccupied(true);
        space.setVehicle(vehicle);

        return toDTO(parkingSpaceRepository.save(space));
    }

    public ParkingSpaceDTO releaseSpace(UUID id) {
        ParkingSpace space = parkingSpaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));
        if (!space.isOccupied()) {
            throw new RuntimeException("Parking space is already free");
        }
        space.setOccupied(false);
        space.setVehicle(null);
        return toDTO(parkingSpaceRepository.save(space));
    }

    private ParkingSpaceDTO toDTO(ParkingSpace space) {
        return new ParkingSpaceDTO(
                space.getId(),
                space.isOccupied(),
                space.getLocation(),
                space.getVehicle() != null ? vehicleService.toDTO(space.getVehicle()) : null);
    }

    private ParkingSpace toEntity(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace space = new ParkingSpace();
        space.setOccupied(parkingSpaceDTO.occupied());
        space.setLocation(parkingSpaceDTO.location());
        if (parkingSpaceDTO.vehicle() != null) {
            space.setVehicle(vehicleService.toVehicle(parkingSpaceDTO.vehicle()));
        }
        return space;
    }
}
