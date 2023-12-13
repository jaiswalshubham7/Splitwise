package com.example.splitwise23.Services;

import com.example.splitwise23.Models.User;
import com.example.splitwise23.Repositories.UserRepository;
import com.example.splitwise23.Strategy.PasswordHashingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHashingStrategy passwordHashingStrategy;

    @Autowired
    public UserService(UserRepository userRepository, PasswordHashingStrategy passwordHashingStrategy){
        this.userRepository = userRepository;
        this.passwordHashingStrategy = passwordHashingStrategy;
    }
    public User registerUser(String name, String email, String password, String phoneNumber){

        //Generate Hash Password
        String hashedPassword = passwordHashingStrategy.hashPassword(password);

        User user = User.builder().name(name)
                .email(email)
                .password(hashedPassword)
                .PhoneNumber(phoneNumber)
                .build();

        return userRepository.save(user);
    }
}
