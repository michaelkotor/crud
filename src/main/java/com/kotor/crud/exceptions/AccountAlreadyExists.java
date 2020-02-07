package com.kotor.crud.exceptions;


import org.springframework.http.HttpStatus;

public class AccountAlreadyExists extends Throwable {
    String message = "Account already exists!";
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public AccountAlreadyExists(String message) {
        this.message = message;
    }

    public AccountAlreadyExists() {}
}
