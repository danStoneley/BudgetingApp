package com.danstoneley.bankingapp;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashTest {
    private User testUser;
    private final UserDAO userDAO = new UserDAO();

    @Test
    void checkPasswordNotStoredPlainText() {
        String testUserPassword = "12345";
        User testUser = TestUtils.createTestUser(testUserPassword);
        String storedHash = TestUtils.retrievePassword(testUser.getId());
        assertNotNull(storedHash); // Hash should not be null
        assertNotEquals("12345", storedHash); // DB should not store the plain password
        assertTrue(storedHash.startsWith("$2"));
        userDAO.deleteUser(testUser.getId());// Stored hash should look like a BCrypt hash
    }
}
