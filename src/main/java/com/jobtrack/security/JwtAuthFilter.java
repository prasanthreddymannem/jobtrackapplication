package com.jobtrack.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jobtrack.service.CustomerUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Component 
public class JwtAuthFilter extends  OncePerRequestFilter{
    private final JwtService jwtService;
    private final CustomerUserDetailsService customerUserDetailsService;

    @Override 
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterchain)
    throws ServletException,IOException
    {
        String authHeader=request.getHeader("Authorization");
        
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterchain.doFilter(request, response);
            return ;
        }

        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            try{
            String token=authHeader.substring(7);
            String email=jwtService.extractEmail(token);
            if(SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=customerUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            }
            catch(Exception exception){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return ;
            }
            filterchain.doFilter(request, response);
        }
        
    }
}
