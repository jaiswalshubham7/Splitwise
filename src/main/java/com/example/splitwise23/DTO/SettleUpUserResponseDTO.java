package com.example.splitwise23.DTO;

import com.example.splitwise23.Models.Transaction;
import com.example.splitwise23.Models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SettleUpUserResponseDTO {
    private String senderName;
    private String receiverName;
    private Long amount;
}
