package com.example.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> accountExceptionHanfler(AccountNotFoundException ac, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), ac.getMessage(), webRequest.getDescription(false), "account not Found");
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    //Genric ExceptionHandler
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponse> genricExceptionHandler(Exception e, WebRequest w){
        ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), e.getMessage(), w.getDescription(false), "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
