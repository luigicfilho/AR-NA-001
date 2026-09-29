import java.util.List;

public class BatchFraudDetection {
    private static final double FRAUD_THRESHOLD = 1000.0;

    public void process(List<Transaction> transactions) {
        long startTime = System.currentTimeMillis();
        System.out.println("\n[Batch] Iniciando processamento em lote...");

        try {
            // Simula o processamento só no final do dia
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}

        for (Transaction t : transactions) {
            if (t.getAmount() > FRAUD_THRESHOLD) {
                long detectionTime = System.currentTimeMillis() - startTime;
                System.out.println("[Batch] FRAUDE DETECTADA: " + t.getId() +
                                   " | Valor: " + t.getAmount() +
                                   " | Tempo desde início: " + detectionTime + " ms");
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("[Batch] Processamento finalizado em " + totalTime + " ms");
    }
}