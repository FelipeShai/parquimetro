package br.com.fiap.parquimetro.domain.services;

import br.com.fiap.parquimetro.domain.repositories.ParkingSpaceRepository;
import br.com.fiap.parquimetro.domain.dtos.TransactionDTO;
import br.com.fiap.parquimetro.domain.entities.Transaction;
import br.com.fiap.parquimetro.domain.repositories.ITransactionRepository;
import br.com.fiap.parquimetro.domain.repositories.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public TransactionDTO createTransaction(UUID vehicleId, UUID parkingSpaceId) {
        var vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        var parkingSpace = parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new RuntimeException("Parking space not found"));

        var transaction = new Transaction(vehicle, parkingSpace, LocalDateTime.now());
        transactionRepository.save(transaction);

        return toDTO(transaction);
    }

    public TransactionDTO completeTransaction(UUID transactionId) {
        var transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setExitTime(LocalDateTime.now());
        transaction.setAmountCharged(calculateAmount(transaction.getEntryTime(), transaction.getExitTime()));
        transactionRepository.save(transaction);

        return toDTO(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateAmount(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toSeconds();
        // R$5 por hora
        BigDecimal hourlyRate = BigDecimal.valueOf(5);
        return hourlyRate.multiply(BigDecimal.valueOf(hours));
    }

    private TransactionDTO toDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getVehicle().getId(),
                transaction.getParkingSpace().getId(),
                transaction.getEntryTime(),
                transaction.getExitTime(),
                transaction.getAmountCharged()
        );
    }
}