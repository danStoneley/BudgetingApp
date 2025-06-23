package com.danstoneley.bankingapp;

import java.util.*;

public class BankingApp {
    private AuthService auth;
    private MenuDisplay display;
    private final Scanner scanner = new Scanner(System.in);

    BankingApp() {
        auth = new AuthService();
        display = new MenuDisplay();

    }
    public void run() {
        while (true) {
            display.showInitialMenu();
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    User user = auth.login();
                    if (user != null) {
                        SessionManager session = new SessionManager(user);
                        session.handleSession();
                    }
                }
                case "2" -> {
                    auth.handleCreateUser();
                }
                case "3" -> {
                    display.showGoodbye();
                    return;
                }
            }
        }
    }
}
