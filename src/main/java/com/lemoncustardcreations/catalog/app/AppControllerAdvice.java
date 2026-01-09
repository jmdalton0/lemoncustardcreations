package com.lemoncustardcreations.catalog.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
