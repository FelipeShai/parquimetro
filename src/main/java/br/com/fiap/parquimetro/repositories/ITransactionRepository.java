package br.com.fiap.parquimetro.repositories;

import br.com.fiap.parquimetro.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
}
