package com.projeto.APIBooks.Validators;

import com.projeto.APIBooks.Exceptions.InvalidEmailException;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator  implements InterfaceValidator{

    @Override
    public String validate(String loginData) {
        if(!(loginData.contains("@") && loginData.contains(".com"))){
            throw new InvalidEmailException(loginData);
        }
        return loginData;
    }
}
