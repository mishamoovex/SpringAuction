package com.lead.service.util;

import com.lead.service.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static User createTestUser() {
        User newUser = new User("Jon", "Smith", "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp());
        return newUser;
    }

    public static User createTestUser(String lastName) {
        User newUser = new User("Jon", lastName, "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp());
        return newUser;
    }

    public static User createUpdatedTestUser(String lastName) {
        User newUser = new User("Jon", lastName, "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp().plusHours(1));
        return newUser;
    }

    public static List<User> createTestUsers(int usersCount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < usersCount; i++) {
            users.add(createTestUser());
        }
        return users;
    }
}
