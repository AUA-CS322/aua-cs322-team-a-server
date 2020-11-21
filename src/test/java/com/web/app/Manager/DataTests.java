package com.web.app.Manager;
import com.web.app.manager.DataManager;

public class DataTests {
    public static void main(String[] args) {
        System.out.println(DataManager.getUser("president").getDepartment());
    }
}