package com.web.app.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataTest {
    @Spy
    @InjectMocks
    private DataManager dataManager;

    @Test
<<<<<<< HEAD
    public void getUserTest()
    {
        Assertions.assertEquals("FName",dataManager.getUser("president").getFirstName());
=======
    public void getUserTest() {
        Assertions.assertEquals("FName", dataManager.getUser("president").getFirstName());
>>>>>>> Search
    }
}
