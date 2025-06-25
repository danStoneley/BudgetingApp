package com.danstoneley.bankingapp.session;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.service.AuthService;
import com.danstoneley.bankingapp.ui.MenuDisplay;

import java.util.*;

public class BankingApp {
    private final AuthService auth;
    private final MenuDisplay display;
    private final Scanner scanner = new Scanner(System.in);

    public BankingApp() {
        auth = new AuthService(new UserDAO());
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
