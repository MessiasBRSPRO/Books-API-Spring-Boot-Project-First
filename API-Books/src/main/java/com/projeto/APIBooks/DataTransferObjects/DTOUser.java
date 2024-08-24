package com.projeto.APIBooks.DataTransferObjects;

import com.projeto.APIBooks.Entities.User;

public record DTOUser(String username, String email, String password) {
    public DTOUser(User user){
        this(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
