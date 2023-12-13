package com.example.splitwise23.DTO;

import com.example.splitwise23.Models.Expense;
import com.example.splitwise23.Models.ExpenseUserType;
import com.example.splitwise23.Models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpenseUserRequestDTO {
    private Long user_id;
    private Long amount;
    private ExpenseUserType expenseUserType;
}
