package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.dao.UserRepository;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.ui.MenuDisplay;
import com.danstoneley.bankingapp.utils.EmailCheck;

import java.util.Scanner;

public class AuthService {
    private final UserRepository repo;
    private final MenuDisplay display = new MenuDisplay();
    private final Scanner scanner = new Scanner(System.in);

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User login() {
        display.showLoginMenu();
        String email = scanner.next();
        String password = scanner.next();
        User user = repo.validateLogin(email, password);
        if (user != null) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Login failed.");
            return null;
        }
    }
    public void handleCreateUser() {
        display.showEnterEmail();
        String email;
        while (true) {
            email = scanner.nextLine().trim();
            if (EmailCheck.emailChecker(email)) {
                break;
            }
            display.showInvalidEmailMessage();
        }
        display.showPasswordEnterPassword();
        String password = scanner.nextLine();
        User newUser = repo.createUser(email, password);
        if (newUser != null) {
            display.showNewUserMessage();
        } else {
            display.showNewUserError();
        }
    }
}


