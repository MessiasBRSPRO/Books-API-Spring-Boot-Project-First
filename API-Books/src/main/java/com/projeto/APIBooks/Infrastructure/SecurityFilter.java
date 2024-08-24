package com.projeto.APIBooks.Infrastructure;


import com.projeto.APIBooks.Repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenGerado = getGeneratedToken(request); // getting the token

        if(tokenGerado != null){
            //Logica de Token(se o token for nulo, o usuario nao existe :))
            // mas se for diferente ,e ele vai filtrar a requisição feita no /login
            var subject = tokenService.getSubject(tokenGerado);
            UserDetails user = userRepository.findByEmail(subject);
            var userAuthenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(userAuthenticate);
        }
        filterChain.doFilter(request, response);
    }

    private String getGeneratedToken(HttpServletRequest request) {
        var tokenExistente = request.getHeader("Authorization"); //Getting the token
        if(tokenExistente != null){
            //if token is distinct of null, the user is logged
            return tokenExistente.replace("Bearer ", "");
        }
        return null; //the user is not logged
    }
}
