package com.danstoneley.bankingapp;

import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.dao.UserDAO;
import com.danstoneley.bankingapp.models.Transaction;
import com.danstoneley.bankingapp.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TransactionDAOTest {
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();
    private User testUser;

    @BeforeEach
    void setUp() {
    testUser = TestUtils.createTestUser("12345");
    }
    @AfterEach
    void tearDown() {
        if (testUser != null) {
            userDAO.deleteUser(testUser.getId());
        }
    }
    @Test
    void shouldCreateTransactionTest() {
        Transaction sent = new Transaction(100, "test", "ref", "+");
        Transaction returned = transactionDAO.createTransaction(new Transaction(100, "test", "ref", "+"), testUser.getId());
        assertEquals(sent, returned); // compares transaction inserted into Database is returned the exact same;
    }
    @Test
    void shouldGetTransactionsTest() {
        Transaction transactionToBeAdded = new Transaction(100, "test", "ref", "+");
        TestUtils.createTransactionList(transactionToBeAdded, testUser.getId(), 5);
        List<Transaction> mockList = TestUtils.createMockTransactionList(transactionToBeAdded, 5);
        assertEquals(mockList, transactionDAO.getTransactions(testUser.getId())); // returned list from Database matches mocked List
    }
    @Test
    void shouldSearchTransactions() {
        Transaction transaction = new Transaction(100, "test", "ref", "+");
        transactionDAO.createTransaction(transaction, testUser.getId());
        List<Transaction> returnedTransactionsFromSearchName = transactionDAO.searchTransactions(testUser.getId(), "test", "name");
        List<Transaction> returnedTransactionsFromSearchRef = transactionDAO.searchTransactions(testUser.getId(),"ref", "reference");
        List<Transaction> returnedTransactionsFromSearchType = transactionDAO.searchTransactions(testUser.getId(),"+", "type");
        assertTrue(returnedTransactionsFromSearchName.contains(transaction)); // searched transaction is valid
        assertTrue(returnedTransactionsFromSearchRef.contains(transaction)); // searched transaction is valid
        assertTrue(returnedTransactionsFromSearchType.contains(transaction)); // searched transaction is valid
    }
}
