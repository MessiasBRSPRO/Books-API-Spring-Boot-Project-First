package com.projeto.APIBooks.Exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String loginData) {
        super("Your email is invalid :"+loginData);
    }
}
