package com.web.app.manager;

import com.web.app.dto.UserInfoDTO;
import com.web.app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class DataTest {
    @Spy
    @InjectMocks
    private DataManager dataManager;

    @Test
    public void getUserTest() {
        Assertions.assertEquals("FName", dataManager.getUser("president").getFirstName());
    }

    @Test
    public void gesUserInfoTest(){Assertions.assertEquals("FName",dataManager.getUserInfo("president").getFirstName());}

    @Test
    public void getUserListTest()
    {
        List<User> users = dataManager.getUserList();
        User user = users.get(2);
        Assertions.assertEquals("FName2", user.getFirstName());
    }

    @Test
    public void getUserMapTest()
    {
        User user = dataManager.getUserById("c2f54384-0fc3-44a4-a3ec-ca250b10dc39");
        Assertions.assertEquals("FName1",user.getFirstName());
    }
}
