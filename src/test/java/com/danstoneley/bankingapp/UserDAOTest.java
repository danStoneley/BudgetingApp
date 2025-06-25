package com.danstoneley.bankingapp;

import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.models.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private final UserDAO userDAO = new UserDAO();
    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = TestUtils.createTestUser();
    }

    @AfterEach
    void tearDown() {
        if (testUser != null) {
            userDAO.deleteUser(testUser.getId());
        }
    }

    @Test
    void shouldCreateUserTest() {
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Checks new test user can Login
    }
    @Test
    void shouldDeleteUserTest() {
        assertTrue(userDAO.deleteUser(testUser.getId())); // Deletes user
    }
    @Test
    void shouldValidateLoginTest(){
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Checks Login works
    }
    @Test
    void shouldAddUserInfoTest() {
       assertEquals(TestUtils.addUserInfo(testUser.getId()), TestUtils.mockProfileInfo()); // Adds user info from Util method into db
    }
    @Test
    void shouldUpdateUserInfoTest() {
        userDAO.addUserInfo(testUser.getId(), "firstNameTest", "lastNameTest", "locationTest", "dobTest");
        String updateTerm = "updatedLocation";
        assertEquals(updateTerm, TestUtils.mockGetUserInfo(testUser.getId(), updateTerm));
    }
    @Test
    void shouldGetUserInfoTest() {
        userDAO.addUserInfo(testUser.getId(), "firstNameTest", "lastNameTest", "locationTest", "dobTest");
        UserProfile userFromDb = userDAO.getUserInfo(testUser.getId());
        UserProfile compareUser = new UserProfile("firstNameTest", "lastNameTest", "locationTest", "dobTest");
        assertEquals(userFromDb.toString(), compareUser.toString());
    }
    @Test
    void shouldUpdatePasswordTest() {
        String newPassword = "newPassword";
        userDAO.updatePassword(newPassword, testUser.getId());
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), newPassword));
    }
}
