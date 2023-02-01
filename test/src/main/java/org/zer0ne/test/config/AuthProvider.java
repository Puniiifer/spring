package org.zer0ne.test.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.zer0ne.test.model.User;
import org.zer0ne.test.repository.UserRepository;


@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userDAO;
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        //получаем пользователя
        User user = userDAO.findByLogin(login);
        //смотрим, найден ли пользователь в базе
        if (user == null) {
            throw new BadCredentialsException("Unknown user " + login);
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }
        UserDetails principal = org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
        return new UsernamePasswordAuthenticationToken(
                principal, password, principal.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
