package com.jobtrack.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobtrack.entity.User;
import com.jobtrack.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override 
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not Found"));

        return  org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRole().name())
            .build();
    }
}
