package com.example.splitwise23.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
