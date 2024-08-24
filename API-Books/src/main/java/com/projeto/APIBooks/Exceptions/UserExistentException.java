package com.projeto.APIBooks.Exceptions;

public class UserExistentException extends RuntimeException {
    public UserExistentException(String message) {
        super(message);
    }
}
