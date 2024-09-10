package br.com.fiap.parquimetro.domain.services;

import br.com.fiap.parquimetro.domain.exceptions.NotFoundException;
import br.com.fiap.parquimetro.domain.dtos.VehicleDTO;
import br.com.fiap.parquimetro.domain.entities.Vehicle;
import br.com.fiap.parquimetro.domain.repositories.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    public VehicleDTO getVehicleById(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found vehicle."));
        return toDTO(vehicle);
    };

    public Collection<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.save(toVehicle(vehicleDTO));
        return toDTO(vehicle);
    }

    public VehicleDTO toDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getLicensePlate(),
                vehicle.getCarModel(),
                vehicle.getEntryTime()
        );
    }

    public Vehicle toVehicle(VehicleDTO vehicle){
        return new Vehicle(
                vehicle.id(),
                vehicle.licensePlate(),
                vehicle.carModel(),
                vehicle.entryTime()
        );
    }
}
