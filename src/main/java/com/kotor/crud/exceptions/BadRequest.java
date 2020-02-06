package com.kotor.crud.exceptions;

public class BadRequest extends Throwable {
    private String message = "Bad Request";
    public BadRequest(String message) {
        this.message = message;
    }

    public BadRequest() {}
}
