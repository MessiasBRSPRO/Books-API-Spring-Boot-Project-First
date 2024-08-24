package com.projeto.APIBooks.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionBody {
    //This class is used to config the error occurred in HTTP communication of app.
    //This class is very Important to ExceptionHandlerClass.

    //This class construct the body of our exceptions or default exceptions than will occurred
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
