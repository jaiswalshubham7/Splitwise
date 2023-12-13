package com.example.splitwise23.Strategy;

import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHashingStrategy implements PasswordHashingStrategy{
    @Override
    public String hashPassword(String password) {
        return password + "hash";
    }
}
