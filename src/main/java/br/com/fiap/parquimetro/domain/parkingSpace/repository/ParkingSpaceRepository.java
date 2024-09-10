package br.com.fiap.parquimetro.domain.parkingSpace.repository;

import br.com.fiap.parquimetro.domain.parkingSpace.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, UUID> {
}
