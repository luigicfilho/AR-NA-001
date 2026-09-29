public class StreamFraudDetection {
    private static final double FRAUD_THRESHOLD = 1000.0;
    private final long startTime;

    public StreamFraudDetection(long startTime) {
        this.startTime = startTime;
    }

    public void process(Transaction transaction) {
        System.out.println("[Stream] Transação recebida: " + transaction.getId() +
                           " | Valor: " + transaction.getAmount());

        if (transaction.getAmount() > FRAUD_THRESHOLD) {
            long detectionTime = System.currentTimeMillis() - startTime;
            System.out.println(">>> [Stream] FRAUDE DETECTADA: " + transaction.getId() +
                               " | Valor: " + transaction.getAmount() +
                               " | Tempo desde início: " + detectionTime + " ms");
        }
    }
}