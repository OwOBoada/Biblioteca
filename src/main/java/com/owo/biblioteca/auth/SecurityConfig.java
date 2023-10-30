package com.ceiba.biblioteca.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configuración de reglas de seguridad
        http
            .authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/prestamo").permitAll() 
                .antMatchers("/prestamo/{id}").permitAll() 
                .anyRequest().authenticated()
                .and()
            .csrf().disable()
            .headers().frameOptions().disable();
    }

    
}
