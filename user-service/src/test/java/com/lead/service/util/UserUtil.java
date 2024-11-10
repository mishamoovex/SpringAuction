package com.lead.service.util;

import com.lead.service.user.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static UserEntity createTestUser() {
        UserEntity newUser = new UserEntity("Jon", "Smith", "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp());
        return newUser;
    }

    public static UserEntity createTestUser(String lastName) {
        UserEntity newUser = new UserEntity("Jon", lastName, "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp());
        return newUser;
    }

    public static UserEntity createUpdatedTestUser(String lastName) {
        UserEntity newUser = new UserEntity("Jon", lastName, "email@gmail.com", "123456");
        newUser.setId("id");
        newUser.setCreateTime(DateTimeUtil.createTimestamp());
        newUser.setUpdateTime(DateTimeUtil.createTimestamp().plusHours(1));
        return newUser;
    }

    public static List<UserEntity> createTestUsers(int usersCount) {
        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i < usersCount; i++) {
            users.add(createTestUser());
        }
        return users;
    }
}
