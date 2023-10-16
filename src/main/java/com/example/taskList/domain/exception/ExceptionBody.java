package com.example.taskList.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionBody {

    private String message;
    private Map<String, String> errors;//we got this message when get exceptions

    public ExceptionBody(String message){
        this.message = message;
    }
}
