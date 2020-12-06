package com.web.app.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.web.app.dto.UserInfoDTO;
import com.web.app.model.User;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataManager {

    public List<User> getUserList() {
        List<User> users = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            FileReader list = new FileReader("users.json");
            try {
                users = objectMapper.readValue(list, new TypeReference<>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String username) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public UserInfoDTO getUserInfo(String username) {
        UserInfoDTO userinfo;
        for (User user : getUserList()) {
            if (user.getUsername().equals(username)) {
                userinfo = new UserInfoDTO(user.getId(), user.getEmail(), user.getPosition(), user.getDepartment(),
                        user.getLocation(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getPhotoUrl());
                return userinfo;
            }
        }
        return null;
    }

    public List<UserInfoDTO> getUsersInfo() {
        List<UserInfoDTO> usersToString = new ArrayList<>();
        getUserList().forEach((user) -> {
            usersToString.add(getUserInfo(user.getUsername()));
        });
        return usersToString;
    }
}