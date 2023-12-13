package com.example.splitwise23.Commands;

import com.example.splitwise23.Controllers.UserController;
import com.example.splitwise23.DTO.RegisterUserRequestDTO;
import com.example.splitwise23.DTO.RegisterUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateUserCommand implements Command{

    private final UserController userController;
    private final Scanner sc;

    @Autowired
    public CreateUserCommand(UserController userController){
        this.userController = userController;
        sc = new Scanner(System.in);
    }

    @Override
    public boolean matches(String command) {
        if (command.equals("CreateUser")){
            return true;
        }
        return false;
    }

    @Override
    public void executeCommand() {
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();

        System.out.println("Enter your Name : ");
        requestDTO.setName(sc.nextLine());

        System.out.println("Enter your Email : ");
        requestDTO.setEmail(sc.nextLine());

        System.out.println("Enter Your Password : ");
        requestDTO.setPassword(sc.nextLine());

        System.out.println("Enter your Phone Number : ");
        requestDTO.setPhoneNumber(sc.nextLine());

        RegisterUserResponseDTO responseDTO =  userController.registerUser(requestDTO);
        System.out.println("User Created with id : " + responseDTO.getUser().getId() + " And Name : " + responseDTO.getUser().getName());
    }
}
