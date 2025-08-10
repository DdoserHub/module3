package com.example.demo.dto.ErrorResponse;

import java.time.Instant;

public class ErrorResponse {
    public Instant timestamp = Instant.now();
    public int status;
    public String error;
    public String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}

