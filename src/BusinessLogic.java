import java.util.List;


public class BusinessLogic {
    private final UserDAO userDAO;
    private final TransactionDAO transactionDAO;
    private final User currentUser;

    BusinessLogic(User user) {
        userDAO = new UserDAO();
        transactionDAO = new TransactionDAO();
        this.currentUser = user;
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
    public void getProfileInfo() {
        System.out.println("GET PROFILE INFO");
    }
    public User createUser(String email, String password){
        return userDAO.createUser(email, password);

    }

}

