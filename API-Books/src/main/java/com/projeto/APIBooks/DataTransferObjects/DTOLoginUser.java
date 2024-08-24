package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.User;
import jakarta.validation.constraints.NotBlank;

public record DTOLoginUser(
        @NotBlank
        String email,
        @NotBlank
        String password) {
    public DTOLoginUser(User user){
        this(user.getEmail(), user.getPassword());
    }
}
