package com.web.app.controller;

import com.web.app.dto.LoginDTO;
import com.web.app.security.JwtTokenProvider;
import com.web.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO loginRequest) throws Exception {
        return ResponseEntity.ok(userService.signin(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @GetMapping("/user")
    public ResponseEntity getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }


}
