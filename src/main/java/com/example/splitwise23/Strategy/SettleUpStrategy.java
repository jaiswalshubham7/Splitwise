package com.example.splitwise23.Strategy;

import com.example.splitwise23.Models.Expense;
import com.example.splitwise23.Models.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense> expenseList);
}
