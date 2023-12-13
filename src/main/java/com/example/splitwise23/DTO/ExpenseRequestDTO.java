package com.example.splitwise23.DTO;

import com.example.splitwise23.Models.ExpenseType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpenseRequestDTO {
    private Long createdByUser_id;
    private String description;
    private Long expenseAmount;
    private ExpenseType expenseType;
    private Long group_id;
    private List<Long> userList;
    private List<ExpenseUserRequestDTO> expenseUserList;
}
