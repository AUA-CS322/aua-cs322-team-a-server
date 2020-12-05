package com.web.app.manager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.web.app.dto.UserInfoDTO;
import com.web.app.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;

@Component
public class DataManager {

    public  List<User> getUserList() {
        List<User> users = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            FileReader list = new FileReader("users.json");
            users = objectMapper.readValue(list, new TypeReference<>() {});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }



    public  User getUser(String username) {
//        for (User user : getUserList()) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
        for(User user: getUsersMap().values()){
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public UserInfoDTO getUserInfo(String username) {
//        for (User user : getUserList()) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
        UserInfoDTO userinfo;
        for (User user : getUsersMap().values()) {
            if (user.getUsername().equals(username)) {
                userinfo = new UserInfoDTO(user);
                if(user.getManager()!=null) {
                    userinfo.setManager(new UserInfoDTO(user.getManager()));
                }
                if(!user.getRelations().isEmpty()){
                    for(User rel: user.getRelations()){
                        userinfo.getRelations().add(new UserInfoDTO(rel));
                    }
                }
                return userinfo;
            }
        }
         return null;
    }

    public  User getUserById(String id) {
        for (User user : getUserList()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


//    public  UserInfoDTO getUserInfoDTObyUsername(String username) {
//        for (UserInfoDTO user : getUsersMap().values()) {
//            if (user.getUsername().equals(username)) {
//                return user;
//            }
//        }
//        return null;
//    }

//    public  UserInfoDTO getUserInfoDTObyKey(String key) {
//        return getUsersMap().get(key);
//    }


    public HashMap<String,User> getUsersMap(){
        UserInfoDTO userinfo;
        JSONArray relations = null;
        HashMap<String,User> usersMap  = new HashMap<String, User>();
        JSONParser parser = new JSONParser();
        //read the relation json into JSONArray
        try {
            FileReader mapping = new FileReader("org-tree.json");
            Object obj = parser.parse(mapping);
            relations = (JSONArray) obj;
        }
        catch (FileNotFoundException e) {
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
        for (User user: getUserList()) {
//            userinfo = new UserInfoDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPosition(), user.getDepartment(),
//                    user.getLocation(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getPhotoUrl());
            usersMap.put(user.getId(),user);
        }
        //find the manager and relations
        for (User user: getUserList()) {
            for (int i = 0; i < relations.size(); i++) {
                JSONObject object = (JSONObject) relations.get(i);
                if (user.getId().equals(object.get("id"))) {
                    usersMap.get(user.getId()).setManager(getUserById((String)object.get("parent")));

                    usersMap.get(object.get("parent")).getRelations().add(user);
                }
            }
        }

        return usersMap;

    }

}