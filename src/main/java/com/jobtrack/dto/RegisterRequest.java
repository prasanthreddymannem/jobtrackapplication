package com.jobtrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter 
public class RegisterRequest {
    
    @NotBlank 
    private String name;

    @NotBlank 
    @Size (min=8,message = "Password must be atleast 8 characters")
    private String password;

    @NotBlank 
    @Email 
    private String email;
}
