import java.util.List;
import java.util.Scanner;

public class SessionManager {
    private final MenuDisplay display;
    private final Scanner scanner;
    private BusinessLogic logic;
    private User currentUser;

    SessionManager(User user) {
        this.display = new MenuDisplay();
        this.scanner = new Scanner(System.in);
        this.logic = new BusinessLogic(user);
        this.currentUser = user;
    }

    public void handleSession() {
        while (true) {
            display.showMainMenu();
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    handleTransactionSession(this.currentUser);
                    break;
                }
                case "2" -> {
                    handleGetBalance();
                    break;
                }
                case "3" -> {
                    handleUserSession(this.currentUser);
                    break;
                }
                case "4" -> {
                    handleLogout();
                    System.out.println("Logged Out");
                    //display.showInitialMenu();
                    return;
                }
                case "5" -> {
                    System.out.println("Exiting");
                    break;
                }
                default -> System.out.println("Invalid Option");
            }
        }
    }
    public void handleUserSession(User user) {
        display.showUserProfileMenu();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> handleProfileInfo();
            case "2" -> handleUpdateProfile();
            case "3" -> handleSession();
        }
    }
    public void handleProfileInfo() {
        display.showProfileInfo();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> logic.getProfileInfo();
        }
    }
    public void handleUpdateProfile() {
        System.out.println("UPDATE INFO");
    }
    public void handleTransactionSession(User user) {
        display.showTransactionMenu();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> handleAddTransaction();
            case "2" -> handleGetTransactions();
            case "3" -> handleFilterTransactions();
            case "4" -> handleSession();
            default -> System.out.println("Invalid Choice");
        }
    }
    public User handleCreateUser() {
        display.showCreateUserOptions();
        String email = scanner.next();
        String password = scanner.next();
        return logic.createUser(email, password);
    }
    public void handleAddTransaction() {
        System.out.println("Enter: Amount, Name, Ref, Type: ");
        double amount = Double.parseDouble(scanner.next());
        String name = scanner.next();
        String ref = scanner.next();
        String type = scanner.next();
        Transaction transactionToAdd = new Transaction(amount, name, ref, type);
        logic.createTransaction(transactionToAdd);
        handleReturnOption();
    }
    public void handleGetTransactions() {
        List<Transaction> transactions = logic.getTransactions();
        for (Transaction t : transactions) {
            System.out.println(t);
            System.out.println();
        }
        handleReturnOption();
    }
    public void handleFilterTransactions() {
        System.out.println("Enter search term then field: ");
        String search = scanner.next();
        String field = scanner.next();
        List<Transaction> transactions = logic.searchTransaction(search, field);
        for (Transaction t : transactions) {
            System.out.println(t);
            System.out.println();
        }
        handleReturnOption();
    }
    public void handleGetBalance() {
        double balance = logic.calcBalance();
        System.out.println("Balance: " + balance);
        handleReturnOption();
    }
    public void handleReturnOption() {
        while (true) {
            display.showReturnOption();
            String choice = scanner.next();
            if (choice.equals("1")) {
                handleSession();
                break;
            } else {
                System.out.println("Select valid option");
            }
        }
    }
    public void handleLogout() {
        currentUser = null;
        logic = null;
    }

}

