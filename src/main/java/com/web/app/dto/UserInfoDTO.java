package com.web.app.dto;

import com.web.app.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
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

    private RelationInfoDTO manager;
    private List<RelationInfoDTO> relations;
    private List<String> relationIds;

    public UserInfoDTO() {
    }

    public UserInfoDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.position = user.getPosition();
        this.department = user.getDepartment();
        this.location = user.getLocation();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.photoUrl = user.getPhotoUrl();
        this.relationIds = new ArrayList<>() {
        };
        relations = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRelationIds() {
        return relationIds;
    }

    public void setRelationIds(List<String> relationIds) {
        this.relationIds = relationIds;
    }

    public void addRelationIds(String RelationId) {
        this.relationIds.add(RelationId);
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



    public List<RelationInfoDTO> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationInfoDTO> relations) {
        this.relations = relations;
    }

    public RelationInfoDTO getManager() {
        return manager;
    }

    public void setManager(RelationInfoDTO manager) {
        this.manager = manager;
    }
}
