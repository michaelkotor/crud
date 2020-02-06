package com.kotor.crud.exceptions;


public class AccountAlreadyExists extends Throwable {
    String message = "Account already exists!";

    public AccountAlreadyExists(String message) {
        this.message = message;
    }

    public AccountAlreadyExists() {}
}
