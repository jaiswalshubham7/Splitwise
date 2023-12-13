package com.example.splitwise23.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private long amount;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;

}
