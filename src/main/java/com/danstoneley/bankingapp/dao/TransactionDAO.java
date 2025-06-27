package com.danstoneley.bankingapp.dao;

import com.danstoneley.bankingapp.config.Database;
import com.danstoneley.bankingapp.exceptions.DataAccessException;
import com.danstoneley.bankingapp.models.Transaction;

import java.sql.*;
import java.util.*;

public class TransactionDAO implements TransactionRepository{
    // creates transaction object and adds to DB //
    @Override
    public Transaction createTransaction(Transaction t, int user_id) {
        String sql = "INSERT INTO transactions (user_id, amount, reference, type, name) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, user_id);
            stmt.setDouble(2, t.getAmount());
            stmt.setString(3, t.getReference());
            stmt.setString(4, t.getType());
            stmt.setString(5, t.getName());
            int rows = stmt.executeUpdate();
            if (rows != 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    return new Transaction(keys.getDouble("amount"), keys.getString("name"), keys.getString("reference"), keys.getString("type"));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("an error occurred", e);
        }
        return null;
    }
    // returns List of all Transactions from DB by user_id //
    @Override
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
            throw new DataAccessException("an error occurred", e);
        }
    }
    // search Transactions by type //
    @Override
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
            throw new DataAccessException("an error occurred", e);
        }
    }

}
