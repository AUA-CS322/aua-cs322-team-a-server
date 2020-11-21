package com.web.app.dto;

import java.util.List;

public class UserInfoDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private String department;
    private String location;
    private String phone;
    private String photoUrl;
    private String Manager_id;
    private List<String> Relations_id;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String id, String email, String position, String department, String location, String firstName, String lastName, String phone, String photoUrl) {
        this.id = id;
        this.email = email;
        this.position = position;
        this.department = department;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }

    public String getManager_id() {
        return Manager_id;
    }

    public void setManager_id(String manager_id) {
        Manager_id = manager_id;
    }

    public List<String> getRelations_id() {
        return Relations_id;
    }

    public void setRelations_id(List<String> relations_id) {
        Relations_id = relations_id;
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
