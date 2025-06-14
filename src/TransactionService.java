import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;
    private final User currentUser;

    TransactionService(User user) {
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
}
