//package com.web.app.security;
//
//import com.web.app.service.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.http.HttpServletRequest;
//
//import static org.mockito.Mockito.doReturn;
//
//@SpringBootTest
//public class JwtTokenProviderTest {
//
//    @Spy
//    @InjectMocks
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Spy
//    @InjectMocks
//    @Autowired
//    private UserService service;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Test
//    public void testCreateToken() {
//
//        String token = jwtTokenProvider.createToken("president");
//        String userName = jwtTokenProvider.getUsername(token);
//        Assertions.assertEquals("president", userName);
//    }
//
//    @Test
//    public void testResolveToken() {
//        doReturn("token").when(request).getHeader("Authorization");
//        String token = jwtTokenProvider.resolveToken(request);
//        Assertions.assertEquals("token", token);
//    }
//
//    @Test
//    public void testSignIn()
//    {
//        ResponseEntity entity = new ResponseEntity(jwtTokenProvider.createToken("President"), HttpStatus.ACCEPTED);
//        Assertions.assertEquals(entity.getHeaders(), service.signin("President","pass").getHeaders());
//    }
//}
