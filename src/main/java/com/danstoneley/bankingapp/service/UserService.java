package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.User;

public class UserService {
    private final UserDAO userDAO;
    private final User currentUser;

    public UserService(User user) {
        userDAO = new UserDAO();
        this.currentUser = user;
    }
    public void addProfile(String firstName, String lastName, String location, String dob) {
        userDAO.addUserInfo(currentUser.getId(), firstName, lastName, location, dob);
    }
    public void updateProfileField(String field, String content) {
        userDAO.updateUserInfo(field, content, currentUser.getId());
    }
    public void getProfileInfo() {
        System.out.println(userDAO.getUserInfo(currentUser.getId()));
    }
    public boolean updatePassword(String newPassword) {
        return userDAO.updatePassword(newPassword, currentUser.getId());
    }
}
