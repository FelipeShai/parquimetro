package br.com.fiap.parquimetro.domain.vehicle.repository;

import br.com.fiap.parquimetro.domain.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, UUID> {

}
