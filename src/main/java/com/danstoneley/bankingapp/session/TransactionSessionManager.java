package com.danstoneley.bankingapp.session;

import com.danstoneley.bankingapp.dao.TransactionDAO;
import com.danstoneley.bankingapp.utils.ReturnHandler;
import com.danstoneley.bankingapp.models.Transaction;
import com.danstoneley.bankingapp.models.User;
import com.danstoneley.bankingapp.service.TransactionService;
import com.danstoneley.bankingapp.ui.MenuDisplay;

import java.util.List;
import java.util.Scanner;

public class TransactionSessionManager {
    private final Scanner scanner;
    private final TransactionService transactionService;
    private final ReturnHandler returnOption;
    private final MenuDisplay display;

    TransactionSessionManager(User user, ReturnHandler returnOption) {
        this.scanner = new Scanner(System.in);
        this.transactionService = new TransactionService(user.getId(), new TransactionDAO());
        this.returnOption = returnOption;
        this.display = new MenuDisplay();
    }
    public void handleAddTransaction() {
        display.showTransactionPrompt();
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
        display.showFilterTransactionPrompt();
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
