package com.kotor.crud.utilityes;

public class RestCheck {
    public static <T> T checkFound(T resource) {
        if(resource == null) {
            throw new RuntimeException("The incoming object is null!");
        }
        return resource;
    }
}
