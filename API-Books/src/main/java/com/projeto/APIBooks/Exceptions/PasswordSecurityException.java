package com.projeto.APIBooks.Exceptions;

public class PasswordSecurityException extends RuntimeException {
    public PasswordSecurityException(String loginData) {
        super("Your password is not security :" +loginData);
    }
}
