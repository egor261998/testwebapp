package com.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String matchId) {
        super("Unknown match: " + matchId);
    }
}
