import java.sql.*;
import java.util.*;

public class TransactionDAO {
    public void createTransaction(Transaction t, int user_id) {
        String sql = "INSERT INTO transactions (user_id, amount, reference, type, name) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            stmt.setDouble(2, t.getAmount());
            stmt.setString(3, t.getReference());
            stmt.setString(4, t.getType());
            stmt.setString(5, t.getInfo());

            int rs = stmt.executeUpdate();
            if (rs != 0) {
                System.out.println("Transaction added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Transaction> getTransactions(int user_id) {
        String sql = "SELECT * FROM transactions WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            List<Transaction> transactionList = new ArrayList<>();
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String name = rs.getString("name");
                String ref = rs.getString("reference");
                String type = rs.getString("type");

                Transaction t = new Transaction(amount, name, ref, type);
                transactionList.add(t);
            }
            return transactionList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public List<Transaction> searchTransactions(int user_id, String searchTerm, String field) {
        List<String> fields = List.of("amount", "reference", "type", "name");
        if (!fields.contains(field)) {
            throw new IllegalArgumentException("Invalid field " + field);
        }
        String sql = "SELECT * FROM transactions WHERE user_id = ? AND " + field + " = ?";
        try (Connection conn = Database.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            stmt.setString(2, searchTerm );
            ResultSet rs = stmt.executeQuery();

            List<Transaction> transactionList = new ArrayList<>();

            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String name = rs.getString("name");
                String ref = rs.getString("reference");
                String type = rs.getString("type");

                Transaction t = new Transaction(amount, name, ref, type);
                transactionList.add(t);

            } if (transactionList.isEmpty()){
                System.out.println("No Transactions found.");
            }
            return transactionList;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
