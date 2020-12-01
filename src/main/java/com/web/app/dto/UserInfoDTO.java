package com.web.app.dto;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String position;
    private String email;
    private String department;
    private String location;
    private String phone;
    private String photoUrl;
    private String managerId;
    private List<String> relationIds;

    public UserInfoDTO() {
    }


    public UserInfoDTO(String id, String username, String email, String position, String department, String location, String firstName, String lastName, String phone, String photoUrl) {
        this.id = id;
        this.email = email;
        this.position = position;
        this.department = department;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.photoUrl = photoUrl;
        this.relationIds = new ArrayList<>() {};
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<String> getRelationIds() {
        return relationIds;
    }

    public void addRelationIds(String RelationId){ this.relationIds.add(RelationId);};

    public void setRelationIds(List<String> relationIds) {
        this.relationIds = relationIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
