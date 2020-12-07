package com.web.app.dto;

import com.web.app.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
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

}
