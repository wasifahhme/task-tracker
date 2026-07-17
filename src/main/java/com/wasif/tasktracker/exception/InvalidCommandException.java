package com.wasif.tasktracker.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message){
        super(message);
    }
}
