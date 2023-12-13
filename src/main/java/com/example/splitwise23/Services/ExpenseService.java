package com.example.splitwise23.Services;

import com.example.splitwise23.DTO.ExpenseRequestDTO;
import com.example.splitwise23.DTO.ExpenseUserRequestDTO;
import com.example.splitwise23.Models.*;
import com.example.splitwise23.Repositories.ExpenseRepository;
import com.example.splitwise23.Repositories.ExpenseUserRepository;
import com.example.splitwise23.Repositories.GroupRepository;
import com.example.splitwise23.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseUserRepository expenseUserRepository;

    @Autowired
    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository, GroupRepository groupRepository, ExpenseUserRepository expenseUserRepository){
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.expenseUserRepository = expenseUserRepository;
    }
    public Expense createExpense(ExpenseRequestDTO expenseRequestDTO){

//        Get User by Id
        Optional<User> optionalUser = userRepository.findById(expenseRequestDTO.getCreatedByUser_id());
        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("Expense User is not exist.");
        }
        User user = optionalUser.stream().findFirst().get();

//        Get Group by Group id
        Optional<Group> optionalGroup = null;
        Group group = null;
        if(expenseRequestDTO.getGroup_id() != null){
            optionalGroup = groupRepository.findById(expenseRequestDTO.getGroup_id());
            group = optionalGroup.stream().findFirst().orElseThrow();
        }

//        Create Expense
        Expense expense = Expense.builder().createdBy(user)
                .totalExpenseAmount(expenseRequestDTO.getExpenseAmount())
                .expenseType(expenseRequestDTO.getExpenseType())
                .description(expenseRequestDTO.getDescription())
                .group(group)
                .build();

        expense = expenseRepository.save(expense);

//        Set Expense User List
//        List<ExpenseUser> expenseUserList = new ArrayList<>();
//        for (ExpenseUserRequestDTO expenseUserRequest : expenseRequestDTO.getExpenseUserList()){
//
//            User expenseUserUser = userRepository.findById(expenseUserRequest.getUser_id()).get();
//            expenseUserList.add(ExpenseUser.builder()
//                    .user(expenseUserUser)
//                    .expense(expense)
//                    .expenseUserType(expenseUserRequest.getExpenseUserType())
//                    .amount(expenseUserRequest.getAmount())
//                    .build());
//        }
//        expense.setExpenseUserList(expenseUserList);

        for (ExpenseUserRequestDTO expenseUserRequest : expenseRequestDTO.getExpenseUserList()){

            User expenseUserUser = userRepository.findById(expenseUserRequest.getUser_id()).get();

            ExpenseUser expenseUser = ExpenseUser.builder()
                    .user(expenseUserUser)
                    .expense(expense)
                    .expenseUserType(expenseUserRequest.getExpenseUserType())
                    .amount(expenseUserRequest.getAmount())
                    .build();

            expenseUserRepository.save(expenseUser);
        }

        expense = expenseRepository.getReferenceById(expense.id);

        return expense;
    }
}
