package com.web.app.service;

 import com.web.app.manager.DataManager;
 import com.web.app.model.User;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
 import com.web.app.security.JwtTokenProvider;

 import javax.xml.crypto.Data;
//import security.JwtTokenProvider;


@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    DataManager dataManager;

    public String signin(String username, String password)   {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
     }
    public User getUser(String username){
        return dataManager.getUser(username);
    }

}
