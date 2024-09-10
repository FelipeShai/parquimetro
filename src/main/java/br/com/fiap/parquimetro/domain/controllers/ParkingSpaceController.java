package br.com.fiap.parquimetro.domain.controllers;

import br.com.fiap.parquimetro.domain.dtos.ParkingSpaceDTO;
import br.com.fiap.parquimetro.domain.services.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parking-spaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        return parkingSpaceService.getAllSpaces();
    }

    @PostMapping("/occupy")
    public ParkingSpaceDTO occupyParkingSpace(@RequestParam UUID id, @RequestParam UUID vehicleId) {
        return parkingSpaceService.occupySpace(id, vehicleId);
    }

    @PostMapping("/release")
    public ParkingSpaceDTO releaseParkingSpace(@RequestParam UUID id) {
        return parkingSpaceService.releaseSpace(id);
    }

    @PostMapping("/create")
    public ParkingSpaceDTO createParkingSpace(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        return parkingSpaceService.createParkingSpace(parkingSpaceDTO);
    }

    @PostMapping("/create-multiple")
    public List<ParkingSpaceDTO> createMultipleParkingSpaces(@RequestParam int numberOfSpaces, @RequestParam String section) {
        return parkingSpaceService.createMultipleParkingSpaces(numberOfSpaces, section);
    }
}
