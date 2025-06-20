import java.time.LocalDateTime;

public class Transaction {
    private final double amount;
    private final String person;
    private final String reference;
    private final String type;
    private final String timestamp;

    public Transaction(double amount, String name, String ref, String type) {
        this.amount = amount;
        this.person = name;
        this.reference = ref;
        this.type = type;
        this.timestamp = String.valueOf(LocalDateTime.now());
    }
    public double getAmount() {
        return amount;
    }
    public String getInfo() {
    return this.person;
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
            return "+£" + amount + " from " + person + " Ref: " + reference;
        } else if (type.equals("-")) {
            return "-£" + amount + " with " + person + " Ref: " + reference;
        }
        return "No valid transaction type entered";
    }
}
