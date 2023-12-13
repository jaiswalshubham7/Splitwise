package com.example.splitwise23.Commands;

import com.example.splitwise23.Controllers.ExpenseController;
import com.example.splitwise23.DTO.ExpenseRequestDTO;
import com.example.splitwise23.DTO.ExpenseUserRequestDTO;
import com.example.splitwise23.Models.ExpenseType;
import com.example.splitwise23.Models.ExpenseUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateExpenseCommand implements Command{
    private final ExpenseController expenseController;
    Scanner sc = new Scanner(System.in);

    @Autowired
    public CreateExpenseCommand(ExpenseController expenseController){
        this.expenseController = expenseController;
    }
    @Override
    public boolean matches(String command) {
        if (command.equals("CreateExpense")){
            return true;
        }
        return false;
    }

    @Override
    public void executeCommand() {
        ExpenseRequestDTO expenseRequest = new ExpenseRequestDTO();

        System.out.println("Expense Created by User id : ");
        expenseRequest.setCreatedByUser_id(sc.nextLong());

        System.out.println("Enter Total Expense Amount : ");
        expenseRequest.setExpenseAmount(sc.nextLong());

        System.out.println("Enter Expense Description : ");
        expenseRequest.setDescription(sc.next());

        System.out.println("Belongs to a group ? Yes or No");
        String isGroup = sc.next();

        if (isGroup.equals("Yes")){
            System.out.println("Enter Group Id : ");
            expenseRequest.setGroup_id(sc.nextLong());
        }

//        Get List of Users involved in Expense
        List<Long> userList = new ArrayList<>();
        System.out.println("Enter Participated User ids : ");
        System.out.println("Enter break once users added.");

        while (true){
            String input = sc.next();
            if (input.equals("break")){
                break;
            }
            userList.add(Long.valueOf(input));
        }
        expenseRequest.setUserList(userList);

//        Get List of User who paid and what amount paid
        System.out.println("Enter Who paid and how much: ");
        List<ExpenseUserRequestDTO> expensePaidUserRequestList = getUserExpenses(ExpenseUserType.Paid);

        System.out.println("Everyone Had to Pay Equally? Yes or No : ");
        String hadToPayConsent = sc.next();

        List<ExpenseUserRequestDTO> expenseOwedUserRequestList = new ArrayList<>();
        if (hadToPayConsent.equals("Yes")){
            Long sharePerUser = expenseRequest.getExpenseAmount()/userList.size();
            for (Long user_id : userList){
                expenseOwedUserRequestList.add(ExpenseUserRequestDTO.builder().user_id(user_id)
                        .amount(sharePerUser)
                        .expenseUserType(ExpenseUserType.HadToPay)
                        .build());
            }
        }else {
//          Get List of User who Owed and what amount owed
            System.out.println("Enter Who Had to pay and how much : ");
            expenseOwedUserRequestList = getUserExpenses(ExpenseUserType.HadToPay);
        }

//        Total ExpenseUserRequests
        List<ExpenseUserRequestDTO> expenseUserRequestDTOList = new ArrayList<>();
        expenseUserRequestDTOList.addAll(expensePaidUserRequestList);
        expenseUserRequestDTOList.addAll(expenseOwedUserRequestList);
        expenseRequest.setExpenseUserList(expenseUserRequestDTOList);

        expenseRequest.setExpenseType(ExpenseType.Original);

        Long expenseId = expenseController.createExpense(expenseRequest);
        System.out.println("Expense Created with id : " + expenseId);

    }
    private List<ExpenseUserRequestDTO> getUserExpenses(ExpenseUserType expenseUserType){

        List<ExpenseUserRequestDTO> expenseUserRequestList = new ArrayList<>();
        System.out.println("Enter break once users added.");

        while (true){
            System.out.println("Enter id of User or break : ");
            String input = sc.next();
            if (input.equals("break")){
                break;
            }
            System.out.println("Enter Amount : ");
            Long amount = sc.nextLong();
            expenseUserRequestList.add(ExpenseUserRequestDTO.builder().user_id(Long.valueOf(input))
                    .amount(amount)
                    .expenseUserType(expenseUserType)
                    .build());
        }
        return expenseUserRequestList;
    }
}
