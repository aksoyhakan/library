package com.example.library.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryExceptionResponse {
    private int status;
    private String message;
    private long timestamp;
}
