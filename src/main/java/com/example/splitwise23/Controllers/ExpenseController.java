package com.example.splitwise23.Controllers;

import com.example.splitwise23.DTO.ExpenseRequestDTO;
import com.example.splitwise23.Models.Expense;
import com.example.splitwise23.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    public Long createExpense(ExpenseRequestDTO expenseRequestDTO){

       return expenseService.createExpense(expenseRequestDTO).getId();
    }
}
