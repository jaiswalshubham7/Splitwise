package com.example.splitwise23.Controllers;

import com.example.splitwise23.DTO.SettleUpUserRequestDTO;
import com.example.splitwise23.DTO.SettleUpUserResponseDTO;
import com.example.splitwise23.Models.Transaction;
import com.example.splitwise23.Services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SettleUpController {

    private final SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService){
        this.settleUpService = settleUpService;
    }
    public List<SettleUpUserResponseDTO> settleUpUser(SettleUpUserRequestDTO settleUpUserRequestDTO){

        List<Transaction> transactionList =  settleUpService.settleUpUser(settleUpUserRequestDTO.getUserId());

        List<SettleUpUserResponseDTO> settleUpUserResponseDTOList = new ArrayList<>();
        for (Transaction transaction : transactionList){
            settleUpUserResponseDTOList.add(SettleUpUserResponseDTO.builder()
                    .senderName(transaction.getSenderUser().getName())
                    .receiverName(transaction.getReceiverUser().getName())
                    .amount(transaction.getAmount())
                    .build());
        }

        return settleUpUserResponseDTOList;
    }
}
