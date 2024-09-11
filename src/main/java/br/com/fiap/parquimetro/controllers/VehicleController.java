package br.com.fiap.parquimetro.controllers;

import br.com.fiap.parquimetro.dtos.VehicleDTO;
import br.com.fiap.parquimetro.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getVehicleById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<VehicleDTO>> findAll() {
        return ResponseEntity.ok(service.getAllVehicles());
    }

    @PostMapping("/insert")
    public ResponseEntity<VehicleDTO> save(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(service.save(vehicleDTO));
    }
}
