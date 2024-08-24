package com.projeto.APIBooks.Exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String theUserMailIsInvalid) {
        super(theUserMailIsInvalid);
    }
}
