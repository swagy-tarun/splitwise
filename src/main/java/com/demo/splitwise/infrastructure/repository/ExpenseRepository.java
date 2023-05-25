package com.demo.splitwise.infrastructure.repository;

import com.demo.splitwise.infrastructure.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
