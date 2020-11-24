package com.web.app.service;

import com.web.app.manager.DataManager;
import com.web.app.model.User;
import com.web.app.security.JwtTokenProvider;
import com.web.app.util.SecurityUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final
    AuthenticationManager authenticationManager;

    private final
    JwtTokenProvider jwtTokenProvider;

    private final
    DataManager dataManager;

    private final SecurityUtils securityUtils;

    public UserService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, DataManager dataManager, SecurityUtils securityUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.dataManager = dataManager;
        this.securityUtils = securityUtils;
    }

    public ResponseEntity signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new ResponseEntity(jwtTokenProvider.createToken(username), HttpStatus.ACCEPTED);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(false);
        }

    }

    public User getUser(String username) {
        return dataManager.getUser(username);
    }

    public User getCurrentUser() {
        final Optional<String> currentUserLogin = securityUtils.getCurrentUserLogin();

        if (currentUserLogin.isEmpty()) {
            throw new IllegalStateException("User must be logged in!");
        }

        final String userLogin = currentUserLogin.get();

        log.debug("Current user login: {}", userLogin);

        User user = dataManager.getUser(userLogin);

        if (user == null) {
            log.error("User \"{}\" not found", userLogin);
            throw new IllegalStateException("User must be logged in!");
        }
        return user;
    }

}