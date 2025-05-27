import java.util.*;

public class BankingApp {
    private UserDAO userDAO;
    private TransactionDAO transactionDAO;
    private User currentUser;

    BankingApp() {
        userDAO = new UserDAO();
        transactionDAO = new TransactionDAO();
    }
    public boolean login(String email, String password) {
        User user = userDAO.validateLogin(email, password);
        if (user != null) {
            this.currentUser = user;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }
    public void logout() {
        currentUser = null;
    }
    public void createTransaction(Transaction t) {
        if (currentUser == null) {
            System.out.println("Must be logged in.");
        } else {
            transactionDAO.createTransaction(t, currentUser.getId());
        }
    }
    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions(currentUser.getId());
    }
    public List<Transaction> searchTransaction(String searchTerm, String fieldTerm) {
        return transactionDAO.searchTransactions(currentUser.getId(), searchTerm, fieldTerm);
    }
    public double calcBalance() {
        List<Transaction> transactionList = getTransactions();
        double amount = 0.0;
        for (Transaction t : transactionList) {
            if (t.getType().equals("+")) {
                amount += t.getAmount();
            } else if (t.getType().equals("-")) {
                amount -= t.getAmount();
            }
        }
        return amount;
    }
    public void updateProfile(String firstName, String lastName, String location, String dob) {
        userDAO.addUserInfo(currentUser.getId(), firstName, lastName, location, dob);
    }
    public void updateProfileField(String field, String content) {
    userDAO.updateUserInfo(field, content, currentUser.getId());
    }
}
