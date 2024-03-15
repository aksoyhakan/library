package com.example.library.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LibraryException extends RuntimeException{

    private HttpStatus status;

    public LibraryException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
