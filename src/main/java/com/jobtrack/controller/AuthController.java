package com.jobtrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.dto.LoginRequest;
import com.jobtrack.dto.LoginResponse;
import com.jobtrack.dto.RegisterRequest;
import com.jobtrack.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/auth")
@RequiredArgsConstructor 
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
       return  userService.loginUser(request);
    }
    
    
}
