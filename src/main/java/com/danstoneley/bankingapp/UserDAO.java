package com.danstoneley.bankingapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User createUser(String email, String password) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            String hashedPassword = PasswordHash.hashPassword(password);
                statement.setString(1, email);
                statement.setString(2, hashedPassword);
                statement.executeUpdate();


            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new User(email, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public User validateLogin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String email_from_db = rs.getString("email");
                String password_from_db = rs.getString("password");
                int id_from_db = rs.getInt("id");

                if (PasswordHash.checkHashedPassword(password, password_from_db)) {
                    return new User(email_from_db, id_from_db);
                } else {
                    System.out.println("Invalid Password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> addUserInfo(int user_id, String firstName, String lastName, String location, String dob) {
        String sql = "INSERT INTO user_profiles (user_id, first_name, last_name, location, birthdate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, location);
            statement.setString(5, dob);

            statement.executeUpdate();

            UserProfile profileInfo = getUserInfo(user_id);
            List<String> profileInfoList = new ArrayList<>();
            profileInfoList.add(profileInfo.getBirthdate());
            profileInfoList.add(profileInfo.getFirstName());
            profileInfoList.add(profileInfo.getLastName());
            profileInfoList.add(profileInfo.getLocation());

            return profileInfoList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public String updateUserInfo(String field, String content, int user_id) {
        List<String> fields = List.of("first_name", "last_name", "location", "birthdate");
        if (!fields.contains(field)) {
            throw new IllegalArgumentException("Invalid field" + field);
        }
        String sql = "UPDATE user_profiles SET " + field + " = ? WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, content);
            statement.setInt(2, user_id);
            statement.executeUpdate();

            return getIndividualUserInfo(field, user_id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public UserProfile getUserInfo(int user_id) {
        String sql = "SELECT * FROM user_profiles WHERE user_id = ?";
        UserProfile userProfile = null;
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String location = rs.getString("location");
                String birthdate = rs.getString("birthdate");
                userProfile = new UserProfile(firstName, lastName, location, birthdate);
            } else {
                System.out.println("No User info found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userProfile;
    }
    public boolean updatePassword(String newPassword, int user_id) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        String hashedPassword = PasswordHash.hashPassword(newPassword);
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, hashedPassword);
            statement.setInt(2, user_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Update Successful");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteUser(int user_id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("User deleted");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getIndividualUserInfo(String field, int user_id) {
        List<String> fields = List.of("first_name", "last_name", "location", "birthdate");
        if (!fields.contains(field)) {
            throw new IllegalArgumentException("Invalid field" + field);
        }
        String sql = "SELECT " + field + " FROM user_profiles WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Information found";
    }
}
