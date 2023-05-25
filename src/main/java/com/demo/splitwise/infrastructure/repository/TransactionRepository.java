package com.demo.splitwise.infrastructure.repository;

import com.demo.splitwise.infrastructure.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
