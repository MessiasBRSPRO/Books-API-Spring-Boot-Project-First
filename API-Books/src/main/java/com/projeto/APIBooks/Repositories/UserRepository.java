package com.projeto.APIBooks.Repositories;

import com.projeto.APIBooks.Entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByusername(@NotBlank String username);

    boolean existsByemail(@NotBlank String email);

    UserDetails findByEmail(String email);
}
