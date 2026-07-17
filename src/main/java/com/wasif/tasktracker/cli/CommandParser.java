package com.wasif.tasktracker.cli;

import org.springframework.stereotype.Component;

import com.wasif.tasktracker.exception.InvalidCommandException;


@Component
public class CommandParser {
    public CommandType parseCommandType(String command){
        switch(command){
            case "add": return CommandType.ADD;
        
            case "update": return CommandType.UPDATE;
    
            case "delete": return CommandType.DELETE;

            case "mark-in-progress": return CommandType.MARK_IN_PROGRESS;

            case "mark-done": return CommandType.MARK_DONE;

            case "list": return CommandType.LIST;


            default: throw new InvalidCommandException("Unknown Command: " + command);
    


        }
    }
}
