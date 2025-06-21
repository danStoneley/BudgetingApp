package com.danstoneley.bankingapp;

import java.util.List;
import java.util.Scanner;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();
    private final MenuDisplay display = new MenuDisplay();
    private final Scanner scanner = new Scanner(System.in);
    private BusinessLogic logic;

    public User login() {
        display.showLoginMenu();
        String email = scanner.next();
        String password = scanner.next();
        User user = userDAO.validateLogin(email, password);
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
            System.out.println("Invalid email. Please try again:");
        }
        display.showPasswordEnterPassword();
        String password = scanner.nextLine();
        User newUser = userDAO.createUser(email, password);
        if (newUser != null) {
            System.out.println("New user created! Please log in.");
        } else {
            System.out.println("Error creating user—please try later.");
        }
    }
}


