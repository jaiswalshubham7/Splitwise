package com.example.splitwise23.Commands;

import com.example.splitwise23.Controllers.SettleUpController;
import com.example.splitwise23.DTO.SettleUpUserRequestDTO;
import com.example.splitwise23.DTO.SettleUpUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class SettleUpUserCommand implements Command{

    private final SettleUpController settleUpController;

    @Autowired
    public SettleUpUserCommand(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }
    @Override
    public boolean matches(String command) {
        return command.equals("SettleUp");
    }

    @Override
    public void executeCommand() {
        System.out.println("User Id for Settle Up : ");
        long userId = new Scanner(System.in).nextLong();

        SettleUpUserRequestDTO settleUpUserRequestDTO = SettleUpUserRequestDTO.builder()
                .userId(userId)
                .build();

        List<SettleUpUserResponseDTO> settleUpUserResponseDTOList =  settleUpController.settleUpUser(settleUpUserRequestDTO);
        if (settleUpUserResponseDTOList.isEmpty()){
            System.out.println("Great, You are already settled up.");
        }
        for (SettleUpUserResponseDTO settleUpUserResponseDTO : settleUpUserResponseDTOList){
            System.out.println(settleUpUserResponseDTO.getSenderName() + " will pay to " + settleUpUserResponseDTO.getReceiverName() + " amount : " + settleUpUserResponseDTO.getAmount());
        }
    }
}
