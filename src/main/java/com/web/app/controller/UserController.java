package com.web.app.controller;

import com.web.app.dto.LoginDTO;
import com.web.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDTO loginRequest) {

        userService.signin(loginRequest.getName(), loginRequest.getPassword());

        return ResponseEntity.ok(userService.signin(loginRequest.getName(), loginRequest.getPassword()));
    }
    @GetMapping("/get")
    public void getUser(@Validated @RequestBody LoginDTO loginRequest) {

    }

}
