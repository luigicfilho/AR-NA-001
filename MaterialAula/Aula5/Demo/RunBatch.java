import java.util.ArrayList;
import java.util.List;

public class RunBatch {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("TXN1", 200.0));
        transactions.add(new Transaction("TXN2", 1500.0)); // FRAUDE
        transactions.add(new Transaction("TXN3", 50.0));
        transactions.add(new Transaction("TXN4", 2500.0)); // FRAUDE
        transactions.add(new Transaction("TXN5", 300.0));

        BatchFraudDetection batchProcessor = new BatchFraudDetection();
        batchProcessor.process(transactions);
    }
}