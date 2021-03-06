package com.web.app.controller;

import com.web.app.dto.LoginDTO;
import com.web.app.dto.LoginResponse;
import com.web.app.manager.SearchManager;
import com.web.app.security.JwtTokenProvider;
import com.web.app.security.MyUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final MyUserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SearchManager searchManager;

    public UserController(MyUserDetailsService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, SearchManager searchManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.searchManager = searchManager;
    }

    @PostMapping(value = "/signin",produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse authenticateUser(@Validated @RequestBody LoginDTO loginRequest) {

        String username = loginRequest.getUsername();
        authenticate(username, loginRequest.getPassword());

        String token = jwtTokenProvider.createToken(username);

        return new LoginResponse(token);
    }

    @GetMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping(value ="/user/{username}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable(name = "username") String username) {
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

    @GetMapping(value ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchUsers(@RequestParam(name = "query") String query) throws IOException, ParseException {
        return ResponseEntity.ok(searchManager.search(query));
    }

}
