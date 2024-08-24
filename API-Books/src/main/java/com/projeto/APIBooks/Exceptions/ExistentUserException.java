package com.projeto.APIBooks.Exceptions;

public class ExistentUserException extends RuntimeException {
    public ExistentUserException(String msg){
        super(msg);
    }
}
