package com.example.splitwise23.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserAmount {
    private User user;
    private Long amount;
}
