package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.dao.UserRepository;
import com.danstoneley.bankingapp.models.User;

public class UserService {
    private final UserRepository repo;
    private final User currentUser;

    public UserService(User user, UserRepository repo) {
        this.repo = repo;
        this.currentUser = user;
    }
    public void addProfile(String firstName, String lastName, String location, String dob) {
        repo.addUserInfo(currentUser.getId(), firstName, lastName, location, dob);
    }
    public void updateProfileField(String field, String content) {
        repo.updateUserInfo(field, content, currentUser.getId());
    }
    public void getProfileInfo() {
        System.out.println(repo.getUserInfo(currentUser.getId()));
    }
    public boolean updatePassword(String newPassword) {
        return repo.updatePassword(newPassword, currentUser.getId());
    }
}
