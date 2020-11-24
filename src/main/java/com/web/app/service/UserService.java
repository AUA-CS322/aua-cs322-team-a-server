package com.web.app.service;

import com.web.app.manager.DataManager;
import com.web.app.model.User;
import com.web.app.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    DataManager dataManager;

    public ResponseEntity signin(String username, String password) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new ResponseEntity(jwtTokenProvider.createToken(username), HttpStatus.ACCEPTED);
        } catch (
                org.springframework.security.core.AuthenticationException e) {
            return ResponseEntity.badRequest().body(false);
        }

    }

    public User getUser(String username) {
        return dataManager.getUser(username);
    }

}