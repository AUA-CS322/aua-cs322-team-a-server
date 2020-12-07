package com.web.app.dto;

import com.web.app.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationInfoDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String position;
    private String username;

    public RelationInfoDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.photoUrl = user.getPhotoUrl();
        this.position = user.getPosition();
        this.username=user.getUsername();
    }

}
