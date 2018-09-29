package com.codedito.userservice.service;

import com.codedito.userservice.domain.ApplicationUser;
import com.codedito.userservice.exception.EnabledUserException;
import com.codedito.userservice.exception.ExpiredAccountException;
import com.codedito.userservice.exception.LockedAccountException;
import com.codedito.userservice.i18m.Messages;
import com.codedito.userservice.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Messages messages;

    @Autowired

    public UserAuthenticationProvider(final AppUserRepository userRepository, final PasswordEncoder passwordEncoder, final Messages messages) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messages = messages;
    }

    @Override
    public Authentication authenticate(final Authentication auth) {

        final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
        final ApplicationUser user = userRepository.findByUsername(token.getName());

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException(messages.get("loginUserUnknown"));
        } else {
            if (!checkIfPasswordExists(token, user)) {
                throw new BadCredentialsException(messages.get("loginUserBadCredintials"));
            } else if (!user.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(messages.get("loginUserCredintialsExpired"));
            } else if (!user.isAccountNonExpired()) {
                throw new ExpiredAccountException(messages.get("loginAccountExpired"));
            } else if (!user.isAccountNonLocked()) {
                throw new LockedAccountException(messages.get("loginUserlocked"));
            } else if (!user.isEnabled()) {
                throw new EnabledUserException(messages.get("loginUserDisabled"));
            }

        }
        return new

                UsernamePasswordAuthenticationToken(user, user.getPassword(), user.

                getAuthorities());
    }


    @Override
    public boolean supports(final Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean checkIfPasswordExists(final UsernamePasswordAuthenticationToken token, final ApplicationUser user) {
        return passwordEncoder.matches(token.getCredentials().toString(), user.getPassword());
    }

}