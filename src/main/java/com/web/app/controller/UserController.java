package com.web.app.controller;

import com.web.app.dto.LoginDTO;
import com.web.app.security.JwtTokenProvider;
import com.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO loginRequest) throws Exception {

        return ResponseEntity.ok(userService.signin(loginRequest.getName(), loginRequest.getPassword()));

    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(userService.getUser(jwtTokenProvider.getUsername(token)));

    }


}
