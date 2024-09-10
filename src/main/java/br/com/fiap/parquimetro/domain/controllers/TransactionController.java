package br.com.fiap.parquimetro.domain.controllers;

import br.com.fiap.parquimetro.domain.dtos.TransactionDTO;
import br.com.fiap.parquimetro.domain.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public TransactionDTO createTransaction(@RequestParam UUID vehicleId, @RequestParam UUID parkingSpaceId) {
        return transactionService.createTransaction(vehicleId, parkingSpaceId);
    }

    @PostMapping("/complete")
    public TransactionDTO completeTransaction(@RequestParam UUID transactionId) {
        return transactionService.completeTransaction(transactionId);
    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
