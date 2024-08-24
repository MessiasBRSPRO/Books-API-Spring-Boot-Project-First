package com.projeto.APIBooks.Infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // indicando pro spring que isso é uma configuração(nos vamos configurar o filtro de requisições, ou seja, vamos decidir qual requisição vai precisar de autenticação ou nao)
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired // Injetando a dependencia e instanciando ela
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain httpRequestFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())//Blocking attacks
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // this API don't Save State,  she use Sessions and cookies to save and load datas
                .authorizeHttpRequests(autorizator -> autorizator.requestMatchers(HttpMethod.POST, "/login", "/registeruser").permitAll() // permitiremos essas requisições do tipo post sem problema nenhum, afinal uma delas é quem vai nos oferecer o token
                        .requestMatchers(HttpMethod.GET, "/books", "/books/bookName={name}", "/books/author={authorName}", "/books/price={price}", "/books/genreBook={genreBook}").permitAll() // essas tbm são permitidas(são funcionalidades de busca de livros)
                        .anyRequest().authenticated() // requisições como criar livros, atualizar livros e remover livros, vao precisar do token
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    @Bean //This method return a Instance of AuthenticationManager/return a bean
    public AuthenticationManager getAuthenticationManagerInstance(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // toda senha de user cadastrada no banco de dados, vai ser Codificada!!
    }
}
