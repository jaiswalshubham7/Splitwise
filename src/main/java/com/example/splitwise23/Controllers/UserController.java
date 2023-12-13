package com.example.splitwise23.Controllers;

import com.example.splitwise23.DTO.RegisterUserRequestDTO;
import com.example.splitwise23.DTO.RegisterUserResponseDTO;
import com.example.splitwise23.Models.User;
import com.example.splitwise23.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO){
        User newUser = userService.registerUser(registerUserRequestDTO.getName(),
                registerUserRequestDTO.getEmail(),
                registerUserRequestDTO.getPassword(),
                registerUserRequestDTO.getPhoneNumber());

        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
        responseDTO.setUser(newUser);

        return responseDTO;
    }

}
