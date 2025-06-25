package com.danstoneley.bankingapp.dao;

import com.danstoneley.bankingapp.models.Transaction;

import java.util.*;

public interface TransactionRepository {
  void createTransaction(Transaction t, int user_id);
  List<Transaction> getTransactions(int user_id);
  List<Transaction> searchTransactions(int user_id, String searchTerm, String field);
    }