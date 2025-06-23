package com.danstoneley.bankingapp;

import java.util.Scanner;

public class UserSessionManager {
    private final Scanner scanner;
    private final UserService userService;
    private final ReturnHandler returnOption;
    private final MenuDisplay display;

    UserSessionManager(User user, ReturnHandler returnOption) {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(user);
        this.returnOption = returnOption;
        this.display = new MenuDisplay();
    }
    public void handleProfileInfo() {
        userService.getProfileInfo();
        returnOption.handleReturnOption();
    }
    public void handleUpdateProfile() {
        String updateField = scanner.next();
        String updateTerm = scanner.next();
        userService.updateProfileField(updateField, updateTerm);
        returnOption.handleReturnOption();
    }
    public void handleChangePassword() {
        String newPassword = scanner.next();
        if (userService.updatePassword(newPassword)) {
            display.showPasswordChanged();
        }
        returnOption.handleReturnOption();
    }
}
