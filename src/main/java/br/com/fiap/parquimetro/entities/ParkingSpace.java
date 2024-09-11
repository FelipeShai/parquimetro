package br.com.fiap.parquimetro.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class ParkingSpace {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.fromString("2de625ef-d075-4184-ae13-60cea0b8f3c0");
    private boolean occupied;
    private String location;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public ParkingSpace() {
    }

    public ParkingSpace(boolean occupied, String location) {
        this.occupied = occupied;
        this.location = location;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}