package com.web.app.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class JwtTokenProviderTest {

    @Spy
    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private HttpServletRequest request;

    @Test
    public void testCreateToken() {
        String token = jwtTokenProvider.createToken("president");
        String userName = jwtTokenProvider.getUsername(token);
        Assertions.assertEquals("president", userName);
    }

    @Test
    public void testResolveToken() {
        doReturn("token").when(request).getHeader("Authorization");
        String token = jwtTokenProvider.resolveToken(request);
        Assertions.assertEquals("token", token);
    }
}
