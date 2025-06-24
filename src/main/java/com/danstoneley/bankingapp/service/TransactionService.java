package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.models.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionDAO transactionDAO;
    private final int userId;

    public TransactionService(int id) {
        transactionDAO = new TransactionDAO();
        this.userId = id;
    }
    public void createTransaction(Transaction t) {
            transactionDAO.createTransaction(t, userId);
    }
    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions(userId);
    }
    public List<Transaction> searchTransaction(String searchTerm, String fieldTerm) {
        return transactionDAO.searchTransactions(userId, searchTerm, fieldTerm);
    }
    public double calcBalance() {
        List<Transaction> transactionList = getTransactions();
        double amount = 0.0;
        for (Transaction t : transactionList) {
            if (t.getType().equals("+")) {
                amount += t.getAmount();
            } else if (t.getType().equals("-")) {
                amount -= t.getAmount();
            }
        }
        return amount;
    }
}
