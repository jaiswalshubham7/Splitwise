package com.example.splitwise23;

import com.example.splitwise23.Commands.CommandExecutor;
import com.example.splitwise23.Commands.CreateExpenseCommand;
import com.example.splitwise23.Commands.CreateUserCommand;
import com.example.splitwise23.Commands.SettleUpUserCommand;
import com.example.splitwise23.Controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaAuditing
public class Splitwise23Application implements CommandLineRunner {

	private final CommandExecutor commandExecutor;
	private final CreateUserCommand createUserCommand;
	private final CreateExpenseCommand createExpenseCommand;
	private final SettleUpUserCommand settleUpUserCommand;
	Scanner sc = new Scanner(System.in);

	@Autowired
	public Splitwise23Application(CommandExecutor commandExecutor, CreateUserCommand createUserCommand, CreateExpenseCommand createExpenseCommand, SettleUpUserCommand settleUpUserCommand){
		this.commandExecutor = commandExecutor;
		this.createUserCommand = createUserCommand;
		this.createExpenseCommand = createExpenseCommand;
		this.settleUpUserCommand = settleUpUserCommand;
	}
	public static void main(String[] args) {
		SpringApplication.run(Splitwise23Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		commandExecutor.addCommand(createUserCommand);
		commandExecutor.addCommand(createExpenseCommand);
		commandExecutor.addCommand(settleUpUserCommand);

		while(true){
			System.out.println("Enter Your Command : ");
			String inputCommand = sc.nextLine();
			commandExecutor.execute(inputCommand);
		}
	}
}
