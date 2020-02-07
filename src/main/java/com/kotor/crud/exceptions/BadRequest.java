package com.kotor.crud.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequest extends RuntimeException {
    private String message = "Bad Request";
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public BadRequest(String message) {
        this.message = message;
    }

    public BadRequest() {}
}
