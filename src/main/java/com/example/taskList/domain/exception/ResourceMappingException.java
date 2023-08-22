package com.example.taskList.domain.exception;


//this exception for jdbc issues
public class ResourceMappingException extends RuntimeException{
    public ResourceMappingException(String message) {
        super(message);
    }
}
