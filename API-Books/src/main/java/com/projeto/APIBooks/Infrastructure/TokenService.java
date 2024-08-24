package com.projeto.APIBooks.Infrastructure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.projeto.APIBooks.Entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(secret); // Utilizaremos o algoritmo de chave assimetrica(Uma chave para criptografar e descriptografar os dados)
        } catch (JWTCreationException exception){
            System.out.println(exception.getMessage());
        }

        return  JWT.create()
                .withIssuer("API_BOOKS") // nome do projeto
                .withSubject(user.getEmail()) // this api will realize authentication with email
                .withExpiresAt(gerarDataExpiracao()) // token tera 2h de validade, quando a validade expirar, o user vai deslogar :)

                .sign(algorithm);
    }

    public String getSubject(String generatedTokenJWT){
        try{
            var algorithmSecurity = Algorithm.HMAC256(secret);
            return JWT.require(algorithmSecurity)
                    .withIssuer("API_BOOKS")
                    .build()
                    .verify(generatedTokenJWT)
                    .getSubject();
        }catch (JWTCreationException e){
            throw new RuntimeException("Token is invalid or expired");
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
