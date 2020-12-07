package com.web.app.controller;

import com.web.app.dto.LoginDTO;
import com.web.app.security.JwtTokenProvider;
import com.web.app.security.MyUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final MyUserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(MyUserDetailsService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider1) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider1;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO loginRequest) {

        String username = loginRequest.getUsername();
        authenticate(username, loginRequest.getPassword());

        String token = jwtTokenProvider.createToken(username);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/user")
    public ResponseEntity getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getUser(@PathVariable(name="username") String username) {
        return ResponseEntity.ok(userService.getUserInfo(username));
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("authenticate() DisabledException " + e.getMessage());
            throw new DisabledException("The user is disabled", e);
        } catch (BadCredentialsException e) {
            log.error("authenticate() BadCredentialsException " + e.getMessage());
            throw new BadCredentialsException("Username and/or password are not correct", e);
        }
    }


}
