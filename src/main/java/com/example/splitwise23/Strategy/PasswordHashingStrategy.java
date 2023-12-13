package com.example.splitwise23.Strategy;

import org.springframework.stereotype.Component;

@Component
public interface PasswordHashingStrategy {
    String hashPassword(String password);
}
