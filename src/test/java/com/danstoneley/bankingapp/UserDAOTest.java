package com.danstoneley.bankingapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private final UserDAO userDAO= new UserDAO();
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
    void createUserTest() {
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Checks new test user can Login
        userDAO.deleteUser(testUser.getId()); // Deletes test user
        assertFalse(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Check delete successful
    }
    @Test
    void deleteUserTest() {
        assertTrue(userDAO.deleteUser(testUser.getId())); // Deletes user
    }
    @Test
    void validateLoginTest(){
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Checks Login works
        userDAO.deleteUser(testUser.getId()); // Deletes test user
        assertFalse(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Checks Login fails
    }
    @Test
    void addUserInfoTest() {
       assertEquals(TestUtils.addUserInfo(testUser.getId()), TestUtils.mockProfileInfo()); // Adds user info from Util method into db
       userDAO.deleteUser(testUser.getId()); // Deletes test user
       assertFalse(TestUtils.isLoginValid(testUser.getEmail(), "12345")); // Check login fails
    }
    @Test
    void updateUserInfoTest() {
        userDAO.addUserInfo(testUser.getId(), "firstNameTest", "lastNameTest", "locationTest", "dobTest");
        String updateTerm = "updatedLocation";
        assertEquals(updateTerm, TestUtils.mockGetUserInfo(testUser.getId(), updateTerm));
    }
    @Test
    void getUserInfoTest() {
        userDAO.addUserInfo(testUser.getId(), "firstNameTest", "lastNameTest", "locationTest", "dobTest");
        UserProfile userFromDb = userDAO.getUserInfo(testUser.getId());
        UserProfile compareUser = new UserProfile("firstNameTest", "lastNameTest", "locationTest", "dobTest");
        assertEquals(userFromDb.toString(), compareUser.toString());
    }
    @Test
    void updatePasswordTest() {
        String newPassword = "newPassword";
        userDAO.updatePassword(newPassword, testUser.getId());
        assertTrue(TestUtils.isLoginValid(testUser.getEmail(), newPassword));
    }
}
