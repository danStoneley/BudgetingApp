package com.danstoneley.bankingapp.session;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.service.BusinessService;
import com.danstoneley.bankingapp.utils.ReturnHandler;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.ui.MenuDisplay;

import java.util.Scanner;

public class SessionManager implements ReturnHandler {
    private final MenuDisplay display;
    private final Scanner scanner;
    private BusinessService logic;
    private final TransactionSessionManager transactionSessionManager;
    private final UserSessionManager userSessionManager;
    private User currentUser;

    public SessionManager(User user) {
        this.display = new MenuDisplay();
        this.scanner = new Scanner(System.in);
        this.logic = new BusinessService(user, new UserDAO());
        this.transactionSessionManager = new TransactionSessionManager(user, this);
        this.userSessionManager = new UserSessionManager(user, this);
        this.currentUser = user;
    }

    public void handleSession() {
        while (true) {
            display.showMainMenu();
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    handleTransactionSession();
                    break;
                }
                case "2" -> {
                    transactionSessionManager.handleGetBalance();
                    break;
                }
                case "3" -> {
                    handleUserSession();
                    break;
                }
                case "4" -> {
                    handleLogout();
                    display.showLogout();
                    return;
                }
                case "5" -> {
                    display.showExiting();
                    break;
                }
                default -> display.showInvalidOption();
            }
        }
    }
    public void handleUserSession() {
        display.showUserProfileMenu();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> userSessionManager.handleProfileInfo();
            case "2" -> handleUpdateProfileField();
            case "3" -> handlePasswordChangeSession();
            case "4" -> handleSession();
            default -> display.showInvalidOption();
        }
    }
    public void handlePasswordChangeSession() {
        display.showPasswordChange();
        userSessionManager.handleChangePassword();
    }
    public void handleTransactionSession() {
        display.showTransactionMenu();
        String choice = scanner.next();
    switch (choice) {
        case "1" -> transactionSessionManager.handleAddTransaction();
        case "2" -> transactionSessionManager.handleGetTransactions();
        case "3" -> transactionSessionManager.handleFilterTransactions();
        case "4" -> handleSession();
        default -> display.showInvalidOption();
        }
    }
    public void handleLogout() {
        currentUser = null;
        logic = null;
        display.showInitialMenu();
    }
    public void handleUpdateProfileField() {
        display.showUpdateProfileField();
        userSessionManager.handleUpdateProfile();
    }
    @Override
    public void handleReturnOption() {
        while (true) {
            display.showReturnOption();
            String choice = scanner.next();
            if (choice.equals("1")) {
                handleSession();
                break;
            } else {
                display.showSelectValidOption();
            }
        }
    }
}

