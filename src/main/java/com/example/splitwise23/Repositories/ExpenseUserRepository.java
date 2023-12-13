package com.example.splitwise23.Repositories;


import com.example.splitwise23.Models.ExpenseUser;
import com.example.splitwise23.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser , Long> {
    List<ExpenseUser> findExpenseUsersByUser_Id(Long user_Id);
}
