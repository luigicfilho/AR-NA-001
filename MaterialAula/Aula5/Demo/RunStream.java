import java.util.ArrayList;
import java.util.List;

public class RunStream {
    public static void main(String[] args) throws InterruptedException {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("TXN1", 200.0));
        transactions.add(new Transaction("TXN2", 1500.0)); // FRAUDE
        transactions.add(new Transaction("TXN3", 50.0));
        transactions.add(new Transaction("TXN4", 2500.0)); // FRAUDE
        transactions.add(new Transaction("TXN5", 300.0));

        long startTime = System.currentTimeMillis();
        StreamFraudDetection streamProcessor = new StreamFraudDetection(startTime);

        System.out.println("\n[Stream] Iniciando processamento em tempo real...");
        for (Transaction t : transactions) {
            Thread.sleep(1000); // Simula chegada em tempo real
            streamProcessor.process(t);
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("[Stream] Processamento finalizado em " + totalTime + " ms");
    }
}