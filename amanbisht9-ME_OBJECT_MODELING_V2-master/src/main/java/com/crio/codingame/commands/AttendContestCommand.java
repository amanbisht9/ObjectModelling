package com.crio.codingame.commands;

import java.io.PrintStream;
import java.util.List;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.services.IUserService;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;

public class AttendContestCommand implements ICommand{

    private final IUserService userService;
    
    public AttendContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute attendContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["ATTEND_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.


    @Override
    public void execute(List<String> tokens) {
        try {
            UserRegistrationDto result = userService.attendContest(tokens.get(1).toString(), tokens.get(2).toString());
            System.out.println(result.toString());
        } catch (ContestNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (UserNotFoundException e1) {
            System.out.println(e1.getMessage());
        }catch (InvalidOperationException e1) {
            System.out.println(e1.getMessage());
        }
    }
    
}
