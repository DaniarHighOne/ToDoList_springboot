package com.example.taskList.service;

import com.example.taskList.web.dto.auth.JwtRequest;
import com.example.taskList.web.dto.auth.JwtResponse;

public interface AuthService {//get and return JWT access tokens

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
