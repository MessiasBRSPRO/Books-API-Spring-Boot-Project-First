package com.projeto.APIBooks.Exceptions;

public class ExistentBookException extends RuntimeException{
    public ExistentBookException(String msg){
        super(msg);
    }
}
