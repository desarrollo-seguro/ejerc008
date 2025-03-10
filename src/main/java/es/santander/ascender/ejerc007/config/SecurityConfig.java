package es.santander.ascender.ejerc007.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/swagger-ui/**").permitAll()  // URLs públicas
                .requestMatchers("/v3/**").permitAll()  // URLs públicas
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/actuator/**").authenticated()
                .anyRequest().denyAll()
            .and()
            .httpBasic()  // Habilita autenticación básica
            .and()
            .csrf().disable();  // Deshabilita CSRF para simplificar la configuración

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("usuario1").password("{noop}contraseña1").roles("USER").build());
        manager.createUser(User.withUsername("usuario2").password("{noop}contraseña2").roles("ADMIN").build());
        manager.createUser(User.withUsername("usuario3").password("{noop}contraseña3").roles("USER").build());
        return manager;
    }
}
