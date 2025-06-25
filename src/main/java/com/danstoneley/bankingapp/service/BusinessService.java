package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.dao.UserRepository;
import com.danstoneley.bankingapp.models.User;

public class BusinessService {
    private final UserRepository repo;

    public BusinessService(User user, UserRepository repo) {
        this.repo = repo;
    }
    public User createUser(String email, String password){
        return repo.createUser(email, password);
    }

}

