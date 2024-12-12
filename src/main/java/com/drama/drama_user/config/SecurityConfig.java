package com.drama.drama_user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") // Aplica esta configuración solo para rutas que comiencen con "/api/"
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register", "/api/users/all").permitAll() // Permite estas rutas sin autenticación
                .anyRequest().authenticated() // Requiere autenticación para todas las demás rutas
            )
            .httpBasic(httpBasic -> httpBasic.realmName("MyRealm")) // Configura autenticación básica
            .formLogin(form -> form.disable()) // Desactiva el formulario de login
            .csrf(csrf -> csrf.disable()); // Desactiva CSRF (si no necesitas proteger contra ataques CSRF)
        
        return http.build();
    }
}
