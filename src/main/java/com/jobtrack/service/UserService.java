package com.jobtrack.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobtrack.dto.LoginRequest;
import com.jobtrack.dto.LoginResponse;
import com.jobtrack.dto.RegisterRequest;
import com.jobtrack.entity.Role;
import com.jobtrack.entity.User;
import com.jobtrack.exception.EmailAlreadyExistsException;
import com.jobtrack.exception.InvalidCredintialException;
import com.jobtrack.repo.UserRepo;
import com.jobtrack.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void registerUser(RegisterRequest registerRequest){
        if(userRepo.existsByEmail(registerRequest.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists "+registerRequest.getEmail());
        }
        String encodedPass=passwordEncoder.encode(registerRequest.getPassword());
        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPass);
        user.setName(registerRequest.getName());
        user.setRole(Role.USER);
        userRepo.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {
        
        User user=userRepo.findByEmail(request.getEmail())
        .orElseThrow(()->new InvalidCredintialException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredintialException("Invalid Email or Password");
        }
        String token=jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}
