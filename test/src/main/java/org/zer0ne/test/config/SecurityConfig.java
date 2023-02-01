package org.zer0ne.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();
        http
        .authenticationManager(authManager(http))
                .authorizeHttpRequests()
                .requestMatchers("/", "/registration", "/hello").permitAll()
                .requestMatchers("/user/**", "/blog/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(toH2Console()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/user", true)
                .and().logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().ignoringRequestMatchers(toH2Console())
                .and()
                .headers().frameOptions().disable();
       return http.build();
    }
}