package com.example.splitwise23.Repositories;

import com.example.splitwise23.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense save(Expense expense);
}
