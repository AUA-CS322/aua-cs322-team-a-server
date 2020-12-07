package com.web.app.model;

import java.util.LinkedList;
import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<User> getRelations() {
        return relations;
    }

    public void setRelations(List<User> relations) {
        this.relations = relations;
    }
}
