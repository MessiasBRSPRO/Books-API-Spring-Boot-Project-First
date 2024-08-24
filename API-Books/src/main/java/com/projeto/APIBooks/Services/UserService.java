package com.projeto.APIBooks.Services;

import com.projeto.APIBooks.Entities.User;
import com.projeto.APIBooks.Exceptions.ExistentUserException;
import com.projeto.APIBooks.Repositories.UserRepository;
import com.projeto.APIBooks.Validators.EmailValidator;
import com.projeto.APIBooks.Validators.PasswordValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;


    public User registerUser(@Valid User user){

        if(userRepository.existsByusername(user.getUsername())){
            throw new ExistentUserException("this Username already used for other User");
        }else if(userRepository.existsByemail(user.getEmail())){
            throw new ExistentUserException("this Email already used for other User");
        }

        if(emailValidator.validate(user.getEmail()) != null && passwordValidator.validate(user.getPassword()) != null){
            user.setPassword(codifyPassword(user.getPassword()));
        }
        return userRepository.save(user);
    }

    private String codifyPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
