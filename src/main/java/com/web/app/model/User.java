package com.web.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class User {
    private String id;
    private String email;
    private String username;
    private String password;
    private String position;
    private String department;
    private String location;
    private String firstName;
    private String lastName;
    private String phone;
    private String photoUrl;

    private User manager;
    private List<User> relations;

    public User() {
        relations = new LinkedList<>();
    }

    public User(String id, String email, String username, String password, String position, String department, String location, String firstName, String lastName, String phone, String photoUrl) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.position = position;
        this.department = department;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.photoUrl = photoUrl;
        relations = new LinkedList<>();
    }


}
