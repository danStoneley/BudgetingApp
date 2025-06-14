import java.util.Scanner;

public class UserSessionManager {
    private final Scanner scanner;
    private final UserService userService;
    private final ReturnHandler returnOption;

    UserSessionManager(User user, ReturnHandler returnOption) {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(user);
        this.returnOption = returnOption;
    }
    public void handleProfileInfo() {
        String choice = scanner.next();
        switch (choice) {
            case "1" -> userService.getProfileInfo();
        }
        returnOption.handleReturnOption();
    }
    public void handleUpdateProfile() {
        System.out.println("UPDATE INFO");
        returnOption.handleReturnOption();
    }
}
