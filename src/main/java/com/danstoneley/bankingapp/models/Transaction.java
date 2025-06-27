package com.danstoneley.bankingapp.models;

import java.util.Objects;

public class Transaction {
    private final double amount;
    private final String name;
    private final String reference;
    private final String type;

    public Transaction(double amount, String name, String ref, String type) {
        this.amount = amount;
        this.name = name;
        this.reference = ref;
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public String getName() {
    return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getReference() {
        return this.reference;
    }
    @Override
    public String toString() {
        if (type.equals("+")) {
            return "+£" + amount + " from " + name + " Ref: " + reference;
        } else if (type.equals("-")) {
            return "-£" + amount + " with " + name + " Ref: " + reference;
        }
        return "No valid transaction type entered";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Transaction that)) return false;
        return (Double.compare(that.amount, amount) == 0
                && Objects.equals(name, that.name)
                && Objects.equals(reference, that.reference)
                && Objects.equals(type, that.type));
        }
    @Override
    public int hashCode() {
        return Objects.hash(amount, name, reference, type);
    }
}
