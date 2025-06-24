package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.User;

public class BusinessService {
    private final UserDAO userDAO;

    public BusinessService(User user) {
        userDAO = new UserDAO();
    }
    public User createUser(String email, String password){
        return userDAO.createUser(email, password);
    }

}

