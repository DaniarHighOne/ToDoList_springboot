package com.example.taskList.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotNull(message= "Username couldn't be null")
    private String username;

    @NotNull(message= "Password couldn't be null")
    private String password;
}
