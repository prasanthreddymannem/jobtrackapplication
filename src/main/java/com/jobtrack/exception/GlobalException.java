package com.jobtrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalException {
    
   @ExceptionHandler (EmailAlreadyExistsException.class)
   public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex){
    return ResponseEntity.status(HttpStatus.CONFLICT)
    .body(ex.getMessage());
   }

   @ExceptionHandler (InvalidCredintialException.class)
   public ResponseEntity<String> handleInvalidCredentials(InvalidCredintialException invalidCredintialException){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(invalidCredintialException.getMessage());
   }
}
