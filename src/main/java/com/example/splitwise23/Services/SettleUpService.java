package com.example.splitwise23.Services;

import com.example.splitwise23.Models.Expense;
import com.example.splitwise23.Models.ExpenseUser;
import com.example.splitwise23.Models.Transaction;
import com.example.splitwise23.Models.User;
import com.example.splitwise23.Repositories.ExpenseUserRepository;
import com.example.splitwise23.Strategy.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SettleUpService {

    private final ExpenseUserRepository expenseUserRepository;
    private final SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpService(ExpenseUserRepository expenseUserRepository, SettleUpStrategy settleUpStrategy){
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
    }
    public List<Transaction> settleUpUser(Long userId){

        // Get All the ExpenseUsers from user
        List<ExpenseUser> expenseUserList = expenseUserRepository.findExpenseUsersByUser_Id(userId);

        // Get unique expenses from expenseUserList
        List<Expense> expenseList = expenseUserList.stream().map(ExpenseUser::getExpense).collect(Collectors.toSet()).stream().toList();

        // Get list of transactions to settle up
        List<Transaction> transactions = settleUpStrategy.settleUp(expenseList);

        // User specific transactions to settle up only the requested user.
        List<Transaction> userTransactions = new ArrayList<>();
        for (Transaction transaction : transactions){
            if(transaction.getSenderUser().getId() == userId || transaction.getReceiverUser().getId() == userId){
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }
}
