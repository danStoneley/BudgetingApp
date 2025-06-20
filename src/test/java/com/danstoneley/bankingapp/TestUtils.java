package com.danstoneley.bankingapp;

import java.util.List;
import java.util.UUID;

public class TestUtils {
    private static final UserDAO user = new UserDAO();

    public static User createTestUser() {
        String uniqueEmail = "test" + UUID.randomUUID() + "@example.com";
        return user.createUser(uniqueEmail, "12345");
    }

    public static boolean isLoginValid(String email, String password) {
        return user.validateLogin(email, password) != null;
    }
    public static List<String> addUserInfo(int user_id) {
        List<String> out =  user.addUserInfo(user_id, "firstName", "lastName", "location", "dob");
        return out;
    }
    public static List<String> mockProfileInfo(){
        return List.of("dob", "firstName", "lastName", "location");
    }
    public static String mockGetUserInfo(int user_id, String updateTerm) {
        return user.updateUserInfo("location", updateTerm, user_id);
    }

}
