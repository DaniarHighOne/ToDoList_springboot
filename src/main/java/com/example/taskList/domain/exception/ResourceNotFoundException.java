package com.example.taskList.domain.exception;

public class ResourceNotFoundException extends RuntimeException {//say to Controller that no any data in DB


    public ResourceNotFoundException(String message) {//create exception with message
        super(message);
    }
}
