package com.example.splitwise23.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends BaseModel{
    private String description;
    private long totalExpenseAmount;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Group group;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<ExpenseUser> expenseUserList;
}
