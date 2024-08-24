package com.projeto.APIBooks.Infrastructure;

import com.projeto.APIBooks.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

//Essa classe é responsavel por livar com os erros na app, ela resolve qualquer erro que acontecer na APP como um todo
//Graças a annotation do Spring ^^
@RestControllerAdvice //this Class will catch all erros than will occures in app
public class ExceptionHandlerClass {

    @ExceptionHandler(ExistentBookException.class)
    public ResponseEntity<ExceptionBody> existentBook(ExistentBookException e, HttpServletRequest pathError){
        String msg = "Entity already exists";
        HttpStatus conflictCodeStatusHttp = HttpStatus.CONFLICT; // error 409(conflict of entities)
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), conflictCodeStatusHttp.value(), msg, e.getMessage(), pathError.getRequestURI());
        return ResponseEntity.status(conflictCodeStatusHttp).body(exceptionBody);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionBody> noSuchElementException(NoSuchElementException e, HttpServletRequest pathError){
        String msg = "Entity not found";
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), 404, msg, e.getMessage(), pathError.getRequestURI());
        return ResponseEntity.status(404).body(exceptionBody);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest pathError){
        String msg = "Fields are invalid or is Empty";
        HttpStatus codeStatus = HttpStatus.BAD_REQUEST;
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), codeStatus.value(), msg, e.getMessage(), pathError.getRequestURI());
        return ResponseEntity.status(codeStatus).body(exceptionBody);
    }

    @ExceptionHandler(ExistentUserException.class)
    public ResponseEntity<ExceptionBody> existentUser(ExistentUserException e, HttpServletRequest path){
        String msg = "The User already exists";
        HttpStatus codeStatus = HttpStatus.CONFLICT;
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), codeStatus.value(), msg, e.getMessage(), path.getRequestURI());
        return ResponseEntity.status(codeStatus).body(exceptionBody);
    }

    @ExceptionHandler(PasswordSecurityException.class)
    public ResponseEntity<ExceptionBody> passwordSecurity(PasswordSecurityException e, HttpServletRequest path){
        String msg = "password is not security";
        HttpStatus codeStatus = HttpStatus.BAD_REQUEST;
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), codeStatus.value(), msg, e.getMessage(), path.getRequestURI());
        return ResponseEntity.status(codeStatus).body(exceptionBody);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionBody> invalidEmail(InvalidEmailException e, HttpServletRequest path){
        String msg = "The email is invalid";
        HttpStatus codeStatus = HttpStatus.BAD_REQUEST;
        ExceptionBody exceptionBody = new ExceptionBody(Instant.now(), codeStatus.value(), msg, e.getMessage(), path.getRequestURI());
        return ResponseEntity.status(codeStatus).body(exceptionBody);
    }

}
