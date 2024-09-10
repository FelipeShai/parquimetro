package br.com.fiap.parquimetro.domain.transactions.repository;

import br.com.fiap.parquimetro.domain.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {
}
