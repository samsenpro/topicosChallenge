package com.software.integration.security;

import com.software.integration.model.Usuario;
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

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()

                                .requestMatchers(HttpMethod.POST, "/registro").permitAll()


                                // topicos ** topicos topicos ** topicos topicos ** topicos topicos ** topicos
                                .requestMatchers(HttpMethod.POST, "/topicos").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                .requestMatchers(HttpMethod.GET, "/topicos").authenticated()

                                .requestMatchers(HttpMethod.PUT, "/topicos/**").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                .requestMatchers(HttpMethod.DELETE, "/topicos/**").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                // topicos ** topicos topicos ** topicos topicos ** topicos topicos ** topicos

                                // cursos ** cursos cursos ** cursos cursos ** cursos cursos ** cursos cursos ** cursos
                                .requestMatchers(HttpMethod.POST, "/cursos").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                .requestMatchers(HttpMethod.GET, "/cursos").authenticated()

                                .requestMatchers(HttpMethod.PUT, "/cursos").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                .requestMatchers(HttpMethod.DELETE, "/cursos").hasAnyAuthority(
                                        Usuario.Rol.ADMINISTRADOR.getAuthority(),
                                        Usuario.Rol.MODERADOR.getAuthority()
                                )
                                // cursos ** cursos cursos ** cursos cursos ** cursos cursos ** cursos cursos ** cursos
                                .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
