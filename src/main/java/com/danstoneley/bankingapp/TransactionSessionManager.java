package com.danstoneley.bankingapp;

import java.util.List;
import java.util.Scanner;

public class TransactionSessionManager {
    private final Scanner scanner;
    private final TransactionService transactionService;
    private final ReturnHandler returnOption;

    TransactionSessionManager(User user, ReturnHandler returnOption) {
        this.scanner = new Scanner(System.in);
        this.transactionService = new TransactionService(user.getId());
        this.returnOption = returnOption;
    }
    public void handleAddTransaction() {
        System.out.println("Enter: Amount, Name, Ref, Type: ");
        double amount = Double.parseDouble(scanner.next());
        String name = scanner.next();
        String ref = scanner.next();
        String type = scanner.next();
        Transaction transactionToAdd = new Transaction(amount, name, ref, type);
        transactionService.createTransaction(transactionToAdd);
        returnOption.handleReturnOption();
    }
    public void handleGetTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        for (Transaction t : transactions) {
            System.out.println(t);
            System.out.println();
        }
        returnOption.handleReturnOption();
    }
    public void handleFilterTransactions() {
        System.out.println("Enter search term then field: ");
        String search = scanner.next();
        String field = scanner.next();
        List<Transaction> transactions = transactionService.searchTransaction(search, field);
        for (Transaction t : transactions) {
            System.out.println(t);
            System.out.println();
        }
        returnOption.handleReturnOption();
    }
    public void handleGetBalance() {
        double balance = transactionService.calcBalance();
        System.out.println("Balance: " + balance);
        returnOption.handleReturnOption();
    }
}
