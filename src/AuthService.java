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
        display.showCreateUserOptions();
        String email = scanner.next();
        String password = scanner.next();
        User newUser = userDAO.createUser(email, password);
        if (newUser != null) {
            System.out.println("New User Created.");
            System.out.println("Please Login");

        } else {
            System.out.println("Error");
        }

    }
}

