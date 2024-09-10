package br.com.fiap.parquimetro.domain.transactions.entity;

import br.com.fiap.parquimetro.domain.parkingSpace.entity.ParkingSpace;
import br.com.fiap.parquimetro.domain.vehicle.entity.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    public Transaction() {}

    public Transaction(Vehicle vehicle, ParkingSpace parkingSpace, LocalDateTime entryTime) {
        this.id = UUID.fromString("7247debe-e600-4abb-90d7-6d7e58c9276b");
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.entryTime = entryTime;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "parking_space_id", nullable = false)
    private ParkingSpace parkingSpace;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private BigDecimal amountCharged;

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setAmountCharged(BigDecimal amountCharged) {
        this.amountCharged = amountCharged;
    }
}
