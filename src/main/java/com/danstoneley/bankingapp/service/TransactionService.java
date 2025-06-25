package com.danstoneley.bankingapp.service;

import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.dao.TransactionRepository;
import com.danstoneley.bankingapp.models.Transaction;

import java.util.List;

public class TransactionService {
    private final int userId;
    private final TransactionRepository repo;

    public TransactionService(int id, TransactionRepository repo) {
        this.repo = repo;
        this.userId = id;
    }
    public void createTransaction(Transaction t) {
            repo.createTransaction(t, userId);
    }
    public List<Transaction> getTransactions() {
        return repo.getTransactions(userId);
    }
    public List<Transaction> searchTransaction(String searchTerm, String fieldTerm) {
        return repo.searchTransactions(userId, searchTerm, fieldTerm);
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
