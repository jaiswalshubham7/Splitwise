package com.example.splitwise23.Commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class CommandExecutor {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command){
        this.commands.add(command);
    }

    public void execute(String inputCommand){
        boolean commandExecuted = false;
        for (Command command : commands){
            if (command.matches(inputCommand)){
                command.executeCommand();
                commandExecuted = true;
                break;
            }
        }
        if (!commandExecuted){
            System.out.println("Invalid Command.");
        }
    }
}
