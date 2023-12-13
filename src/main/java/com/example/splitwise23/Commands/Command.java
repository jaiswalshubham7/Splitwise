package com.example.splitwise23.Commands;

import org.springframework.stereotype.Component;

public interface Command {
    boolean matches(String command);
    void executeCommand();
}
