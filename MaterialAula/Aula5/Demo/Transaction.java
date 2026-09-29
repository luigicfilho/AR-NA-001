import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}