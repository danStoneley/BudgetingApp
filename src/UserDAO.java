import java.sql.*;
import java.util.List;

public class UserDAO {
    public User createUser(String username, String password) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new User(username, password, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public User validateLogin(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String email_from_db = rs.getString("email");
                String password_from_db = rs.getString("password");
                int id_from_db = rs.getInt("id");

                if (password_from_db.equals(password)) {
                    return new User(email_from_db, password_from_db, id_from_db);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addUserInfo(int user_id, String firstName, String lastName, String location, String dob) {
        String sql = "INSERT INTO user_profiles (user_id, first_name, last_name, location, birthdate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, location);
            statement.setString(5, dob);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUserInfo(String field, String content, int user_id) {
        List<String> fields = List.of("first_name", "last_name", "location", "birthdate");
        if (!fields.contains(field)) {
            throw new IllegalArgumentException("Invalid field" + field);
        }
        String sql = "UPDATE user_profiles SET " + field + " = ? WHERE user_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, content);
            statement.setInt(2, user_id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUserInfo() {

    }
}
