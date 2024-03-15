package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler
    ResponseEntity<LibraryExceptionResponse> handleException(LibraryException libraryException){
        LibraryExceptionResponse response=new LibraryExceptionResponse(libraryException.getStatus().value(), libraryException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,libraryException.getStatus());
    }
    @ExceptionHandler
    ResponseEntity<LibraryExceptionResponse> handleException(Exception exception){
        LibraryExceptionResponse response=new LibraryExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
