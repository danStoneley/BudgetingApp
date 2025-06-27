package com.danstoneley.bankingapp;

import com.danstoneley.bankingapp.config.Database;
import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.exceptions.DataAccessException;
import com.danstoneley.bankingapp.models.Transaction;
import com.danstoneley.bankingapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    private static final UserDAO user = new UserDAO();
    private static final TransactionDAO transactionDAO = new TransactionDAO();

    public static User createTestUser(String password) {
        String uniqueEmail = "test" + UUID.randomUUID() + "@example.com";
        return user.createUser(uniqueEmail, password);
    }
    public static boolean isLoginValid(String email, String password) {
        return user.validateLogin(email, password) != null;
    }
    public static List<String> addUserInfo(int user_id) {
        return user.addUserInfo(user_id, "firstName", "lastName", "location", "dob");
    }
    public static List<String> mockProfileInfo(){
        return List.of("dob", "firstName", "lastName", "location");
    }
    public static String mockGetUserInfo(int user_id, String updateTerm) {
        return user.updateUserInfo("location", updateTerm, user_id);
    }
    public static void createTransactionList(Transaction t, int user_id, int count) {
        for (int i = 0; i < count; i++) {
            transactionDAO.createTransaction(t, user_id);
        }
    }
    public static List<Transaction> createMockTransactionList(Transaction t, int count) {
        List<Transaction> mockTransactionList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mockTransactionList.add(t);
        }
        return mockTransactionList;
    }

    public static String retrievePassword(int user_id) {
        String sql = "SELECT password FROM users WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            throw new DataAccessException("an error occurred", e);
        }
        return "no password";
    }
}
