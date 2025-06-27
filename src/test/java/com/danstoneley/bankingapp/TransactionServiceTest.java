package com.danstoneley.bankingapp;

import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.Transaction;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.service.TransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TransactionServiceTest {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private User testUser;
    private TransactionService transactionService;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        testUser = TestUtils.createTestUser("12345");
        this.transactionService = new TransactionService(testUser.getId(), transactionDAO);
        this.userDAO = new UserDAO();
    }
    @AfterEach
    void tearDown() {
        if (testUser != null) {
            userDAO.deleteUser(testUser.getId());
        }
    }
    @Test
    void shouldCalculateBalance() {
        double amount = 150;
        int count = 5;
        Transaction mockedTransaction = new Transaction(amount, "test", "ref", "+");
        TestUtils.createMockTransactionList(mockedTransaction, count);
        TestUtils.createTransactionList(mockedTransaction, testUser.getId(), count);
        double mockedBalance = amount * count;
        assertEquals(mockedBalance, transactionService.calcBalance());
    }
}
