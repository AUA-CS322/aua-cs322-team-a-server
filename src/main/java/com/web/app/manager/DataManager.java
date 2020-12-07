package com.web.app.manager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.web.app.dto.RelationInfoDTO;
import com.web.app.dto.UserInfoDTO;
import com.web.app.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataManager {

    public List<User> getUserList() {
        List<User> users = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            InputStreamReader userDataFileReader = new InputStreamReader(
                    new ClassPathResource("data/users.json").getInputStream());
            users = objectMapper.readValue(userDataFileReader, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }


    public User getUser(String username) {
        for (User user : getUsersMap().values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public UserInfoDTO getUserInfo(String username) {
        UserInfoDTO userinfo;
        for (User user : getUsersMap().values()) {
            if (user.getUsername().equals(username)) {
                userinfo = new UserInfoDTO(user);
                if (user.getManager() != null) {
                    userinfo.setManager(new RelationInfoDTO(user.getManager()));
                }
                if (!user.getRelations().isEmpty()) {
                    for (User rel : user.getRelations()) {
                        userinfo.getRelations().add(new RelationInfoDTO(rel));
                    }
                }
                return userinfo;
            }
        }
        return null;
    }

    public User getUserById(String id) {
        for (User user : getUserList()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


    public HashMap<String, User> getUsersMap() {
        JSONArray relations = null;
        HashMap<String, User> usersMap = new HashMap<String, User>();
        JSONParser parser = new JSONParser();
        //read the relation json into JSONArray
        try {
            InputStreamReader orgTreeDataFileReader = new InputStreamReader(
                    new ClassPathResource("data/org-tree.json").getInputStream());
            Object obj = parser.parse(orgTreeDataFileReader);
            relations = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (User user : getUserList()) {
            usersMap.put(user.getId(), user);
        }
        //find the manager and relations
        for (User user : getUserList()) {
            for (int i = 0; i < relations.size(); i++) {
                JSONObject object = (JSONObject) relations.get(i);
                if (user.getId().equals(object.get("id"))) {
                    usersMap.get(user.getId()).setManager(getUserById((String) object.get("parent")));

                    usersMap.get(object.get("parent")).getRelations().add(user);
                }
            }
        }

        return usersMap;

    }

}