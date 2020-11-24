package com.web.app.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * Utility class for Spring Security.
 */
@Component
public class SecurityUtils {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user
     */
    public Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }


    /**
     * Get the JWT of the current username.
     *
     * @return the JWT of the current username
     */
    public Optional<String> getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    String token = (String) authentication.getCredentials();
                    if (StringUtils.hasText(token)) {
                        return Jwts.parser()
                                .setSigningKey(secretKey)
                                .parseClaimsJws(token)
                                .getBody().getSubject();
                    }
                    return null;
                });
    }
}
