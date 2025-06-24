package com.danstoneley.bankingapp.ui;

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
    public void showGoodbye() {
        System.out.println("Goodbye");
    }
    public void showLogout() {
        System.out.println("Logged Out");
    }
    public void showExiting() {
        System.out.println("Exiting...");
    }
    public void showInvalidOption() {
        System.out.println("Invalid Option");
    }
    public void showSelectValidOption() {
        System.out.println("Select Valid Option");
    }
    public void showPasswordChanged() {
        System.out.println("Password Changed");
    }
    public void showInvalidEmailMessage() {
        System.out.println("Invalid email. Please try again:");
    }
    public void showNewUserMessage() {
        System.out.println("New user created! Please log in.");
    }
    public void showNewUserError() {
        System.out.println("Error creating user — please try later.");
    }
    public void showTransactionPrompt() {
        System.out.println("Enter: Amount, Name, Ref, Type: ");
    }
    public void showFilterTransactionPrompt() {
        System.out.println("Enter search term then field: ");
    }
}
