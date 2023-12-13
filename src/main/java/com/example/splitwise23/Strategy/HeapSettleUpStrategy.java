package com.example.splitwise23.Strategy;

import com.example.splitwise23.Models.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(List<Expense> expenseList) {

        HashMap<User, Long> usersTotalAmountMap = new HashMap<>();

        // Calculate the Net Amount of each user
        for (Expense expense : expenseList){
            for (ExpenseUser expenseUser : expense.getExpenseUserList()){
                if (expenseUser.getExpenseUserType().equals(ExpenseUserType.Paid)){
                    User paidExpenseUser = expenseUser.getUser();
                    if(usersTotalAmountMap.containsKey(paidExpenseUser)){
                        usersTotalAmountMap.put(paidExpenseUser, usersTotalAmountMap.get(paidExpenseUser) + expenseUser.getAmount());
                    }else{
                        usersTotalAmountMap.put(paidExpenseUser, expenseUser.getAmount());
                    }
                }else if (expenseUser.getExpenseUserType().equals(ExpenseUserType.HadToPay)){
                    User owedExpenseUser = expenseUser.getUser();
                    if(usersTotalAmountMap.containsKey(owedExpenseUser)){
                        usersTotalAmountMap.put(owedExpenseUser, usersTotalAmountMap.get(owedExpenseUser) - expenseUser.getAmount());
                    }else{
                        usersTotalAmountMap.put(owedExpenseUser, - expenseUser.getAmount());
                    }
                }
            }
        }

        List<UserAmount> userAmounts = new ArrayList<>();
        for(User user : usersTotalAmountMap.keySet()){
            userAmounts.add(new UserAmount(user, usersTotalAmountMap.get(user)));
        }

        Queue<UserAmount> paidUsers = new PriorityQueue<>((o1, o2) -> (int) (o2.getAmount() - o1.getAmount()));
        Queue<UserAmount> owedUsers = new PriorityQueue<>((o1, o2) -> (int) (o1.getAmount() - o2.getAmount()));

        // Add paid users to maxHeap and owed users to minHeap
        for (UserAmount userAmount : userAmounts){
            if (userAmount.getAmount() > 0){
                paidUsers.add(userAmount);
            } else if (userAmount.getAmount() < 0) {
                owedUsers.add(userAmount);
            }
        }

        long remainingAmount = 0;
        List<Transaction> transactions = new ArrayList<>();

        // Calculate the transactions.
        while (!paidUsers.isEmpty() && !owedUsers.isEmpty()){
            UserAmount paidUserAmount = paidUsers.poll();
            UserAmount owedUserAmount = owedUsers.poll();

            if(paidUserAmount.getAmount() >= owedUserAmount.getAmount()){
                // Create a transaction
                transactions.add(new Transaction(owedUserAmount.getUser(), paidUserAmount.getUser(), Math.abs(owedUserAmount.getAmount())));

                remainingAmount = paidUserAmount.getAmount() - owedUserAmount.getAmount();
                if (paidUserAmount.getAmount() > remainingAmount){
                    paidUserAmount.setAmount(remainingAmount);
                    paidUsers.add(paidUserAmount);
                }
            }else {
                // Create a transaction
                transactions.add(new Transaction(owedUserAmount.getUser(), paidUserAmount.getUser(), Math.abs(paidUserAmount.getAmount())));

                remainingAmount = owedUserAmount.getAmount() - paidUserAmount.getAmount();
                owedUserAmount.setAmount(remainingAmount);
                owedUsers.add(owedUserAmount);
            }
        }

        return transactions;
    }
}
