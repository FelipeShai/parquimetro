package br.com.fiap.parquimetro.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_veiculo")
public class Vehicle {
    public Vehicle(UUID id, String licensePlate, String carModel, LocalDateTime entryTime) {
        this.id = UUID.fromString("1de625ef-d075-4184-ae13-60cea0b8f3c0");
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.entryTime = entryTime;
    }

    public Vehicle() {
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String licensePlate;
    private String carModel;
    private LocalDateTime entryTime;

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}
