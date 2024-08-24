package com.projeto.APIBooks.Validators;

import com.projeto.APIBooks.Exceptions.PasswordSecurityException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class PasswordValidator implements InterfaceValidator{

    @Override
    public String validate(String loginData) {
        String regexPassword = "^[a-zA-Z]{6,}+[0-9]{1,}+[!@#$%¨&*()_+=-]{1,}$";
        Pattern pattern = Pattern.compile(regexPassword);
        Matcher patternInPassword = pattern.matcher(loginData);
        boolean passwordIsValid = patternInPassword.find();
        if(!(passwordIsValid)){
            throw new PasswordSecurityException(loginData);
        }
        return loginData;
    }
}
