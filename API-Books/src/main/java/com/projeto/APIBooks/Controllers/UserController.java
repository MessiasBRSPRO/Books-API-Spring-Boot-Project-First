package com.projeto.APIBooks.Controllers;

import com.projeto.APIBooks.DataTransferObjects.DTOLoginUser;
import com.projeto.APIBooks.DataTransferObjects.DTOToken;
import com.projeto.APIBooks.Entities.User;
import com.projeto.APIBooks.Infrastructure.TokenService;
import com.projeto.APIBooks.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager gerenciadorAutenticacao;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @RequestMapping("/registeruser")
    public ResponseEntity<User> registerUserEndpoint(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder){
        URI uri = uriBuilder.path("/registeruser/id={id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userService.registerUser(user));
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DTOLoginUser loginUser){
        var token =  new UsernamePasswordAuthenticationToken(loginUser.email(), loginUser.password());
        var autenticacao = gerenciadorAutenticacao.authenticate(token);
        var tokenJWTGenerated = tokenService.generateToken((User) autenticacao.getPrincipal());
        return ResponseEntity.ok(new DTOToken(tokenJWTGenerated));
    }

}
