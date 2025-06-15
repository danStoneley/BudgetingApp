import java.util.Scanner;

public class SessionManager implements ReturnHandler {
    private final MenuDisplay display;
    private final Scanner scanner;
    private BusinessLogic logic;
    private final TransactionSessionManager transactionSessionManager;
    private final UserSessionManager userSessionManager;
    private User currentUser;

    SessionManager(User user) {
        this.display = new MenuDisplay();
        this.scanner = new Scanner(System.in);
        this.logic = new BusinessLogic(user);
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
    public void handleUserSession() {
        display.showUserProfileMenu();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> userSessionManager.handleProfileInfo();
            case "2" -> userSessionManager.handleUpdateProfile();
            case "3" -> handleSession();
        }
    }
    public void handleTransactionSession() {
        display.showTransactionMenu();
        String choice = scanner.next();
        switch (choice) {
            case "1" -> transactionSessionManager.handleAddTransaction();
            case "2" -> transactionSessionManager.handleGetTransactions();
            case "3" -> transactionSessionManager.handleFilterTransactions();
            case "4" -> handleSession();
            default -> System.out.println("Invalid Choice");
        }
    }
    public void handleLogout() {
        currentUser = null;
        logic = null;
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
                System.out.println("Select valid option");
            }
        }
    }
}

