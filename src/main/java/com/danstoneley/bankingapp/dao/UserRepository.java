package com.danstoneley.bankingapp.dao;

import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.models.UserProfile;

import java.util.*;

public interface UserRepository {
    User createUser(String email, String password);
    User validateLogin(String email, String password);
    List<String> addUserInfo(int user_id, String firstName, String lastName, String location, String dob);
    String updateUserInfo(String field, String content, int user_id);
    UserProfile getUserInfo(int user_id);
    boolean updatePassword(String newPassword, int user_id);
    boolean deleteUser(int user_id);
    String getIndividualUserInfo(String field, int user_id);
}
