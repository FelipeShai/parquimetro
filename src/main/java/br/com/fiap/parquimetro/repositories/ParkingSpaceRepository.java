package br.com.fiap.parquimetro.repositories;

import br.com.fiap.parquimetro.entities.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, UUID> {
}
