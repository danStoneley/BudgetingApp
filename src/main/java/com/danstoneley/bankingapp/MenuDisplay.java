package com.danstoneley.bankingapp;

public class MenuDisplay {
    public void showLoginMenu() {
        System.out.println("Enter Email: ");
        System.out.println("Enter Password: ");
    }
    public void showInitialMenu() {
        System.out.println("===== Banking =====");
        System.out.println("[1] Login");
        System.out.println("[2] Create User");
        System.out.println("[3] Exit");
    }
    public void showMainMenu() {
        System.out.println("[1] Transactions");
        System.out.println("[2] Balance");
        System.out.println("[3] Profile");
        System.out.println("[4] Logout");
        System.out.println("[5] Exit");
    }
    public void showTransactionMenu() {
        System.out.println("[1] Add Transaction");
        System.out.println("[2] Show Transactions");
        System.out.println("[3] Filter Transactions");
        System.out.println("[4] Main Menu");

    }
    public void showUserProfileMenu() {
        System.out.println("[1] Show Profile Info");
        System.out.println("[2] Update Profile");
        System.out.println("[3] Change Password");
        System.out.println("[4] Main Menu");
    }
    public void showReturnOption() {
        System.out.println("[1] Return to Main Menu");
    }
    public void showEnterEmail() {
        System.out.println("Enter Email: ");
    }
    public void showPasswordEnterPassword() {
        System.out.println("Enter Password: ");
    }
    public void showPasswordChange() {
        System.out.println("Enter New Password: ");
    }
    public void showUpdateProfileField() {
        System.out.println("Enter field to be updated: ");
        System.out.println("Enter new information: ");
    }
}
