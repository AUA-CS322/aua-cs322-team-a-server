package com.web.app.security;

import com.web.app.dto.UserInfoDTO;
import com.web.app.manager.DataManager;
import com.web.app.model.User;
import com.web.app.util.SecurityUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class MyUserDetailsService implements UserDetailsService {

    private final DataManager dataManager;

    private final SecurityUtils securityUtils;

    public MyUserDetailsService(DataManager dataManager, SecurityUtils securityUtils) {
        this.dataManager = dataManager;
        this.securityUtils = securityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = getUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public User getUser(String username) {
        return dataManager.getUser(username);
    }


    public UserInfoDTO getUserInfo(String username) {
        return dataManager.getUserInfo(username);
    }

    public User getCurrentUser() {
        final Optional<String> currentUserLogin = securityUtils.getCurrentUserLogin();

        if (currentUserLogin.isEmpty()) {
            throw new IllegalStateException("User must be logged in!");
        }

        final String userLogin = currentUserLogin.get();

        log.debug("Current user login: {}", userLogin);

        User user = dataManager.getUser(userLogin);

        if (user == null) {
            log.error("User \"{}\" not found", userLogin);
            throw new IllegalStateException("User must be logged in!");
        }
        return user;
    }

}
