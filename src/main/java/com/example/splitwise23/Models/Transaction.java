package com.example.splitwise23.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Transaction {
    private User senderUser;
    private User receiverUser;
    private Long amount;
}
