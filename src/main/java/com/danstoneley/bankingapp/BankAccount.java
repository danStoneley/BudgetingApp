package com.danstoneley.bankingapp;

import java.util.ArrayList;

public class BankAccount {
    private double balance;
    private final String user;
    private final ArrayList<Transaction> transactionHistory;

    public BankAccount(String name){
        this.balance = 0.0;
        this.user = name;
        this.transactionHistory = new ArrayList<>();

    }
  public void deposit(Transaction add) {
        if (add.getAmount() > 0) {
            balance += add.getAmount();
            transactionHistory.add(add);
        }
    }
    public void withdrawal(Transaction withdraw ) {
        if (withdraw.getAmount() > 0 && withdraw.getAmount() <= balance){
            balance -= withdraw.getAmount();
            transactionHistory.add(withdraw);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    public double getBalance() {
        return balance;
    }
    public String getUser() {
        return user;
    }
    public ArrayList<Transaction> getHistory() {
        return transactionHistory;
    }
}
