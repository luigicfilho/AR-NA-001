import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProcessamentoBatchVendas {
    
    public static void main(String[] args) {
        long tempoInicio = System.currentTimeMillis();
        String arquivoCSV = "Coffe_sales.csv";
        Map<String, Double> vendasPorCafe = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha = br.readLine();
            
            while ((linha = br.readLine()) != null) {
                processarLinhaVenda(linha, vendasPorCafe);
            }
            
            exibirResultados(vendasPorCafe);
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;

        System.out.println("\n=== TEMPO DE PROCESSAMENTO ===");
        System.out.printf("Tempo total: %d milissegundos%n", tempoTotal);
        System.out.printf("Tempo total: %.2f segundos%n", tempoTotal / 1000.0);
    }
    
    private static void processarLinhaVenda(String linha, Map<String, Double> vendasPorCafe) {
        String[] dados = parseCSVLine(linha);
        
        if (dados.length >= 7) {
            String nomeCafe = dados[6].trim();
            String valorStr = dados[5].trim();
            
            try {
                double valor = converterValor(valorStr);
                
                vendasPorCafe.put(nomeCafe, vendasPorCafe.getOrDefault(nomeCafe, 0.0) + valor);
            } catch (ParseException e) {
                System.err.println("Erro ao converter valor: " + valorStr);
            }
        }
    }
    
    private static String[] parseCSVLine(String line) {
        String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        return line.split(regex, -1);
    }

    private static double converterValor(String valorStr) throws ParseException {
        if (valorStr == null || valorStr.trim().isEmpty()) {
            throw new ParseException("Valor nulo ou vazio", 0);
        }
        
        String valorNumerico = valorStr.replace("\"", "").replace(" ", "").trim();
        valorNumerico = valorNumerico.replace("R", "").replace(",", ".");
        
        try {
            return Double.parseDouble(valorNumerico);
        } catch (NumberFormatException e) {
            throw new ParseException("Formato inválido: " + valorStr, 0);
        }
    }
    
    private static void exibirResultados(Map<String, Double> vendasPorCafe) {
        System.out.println("=== RESULTADOS DO PROCESSAMENTO BATCH ===");
        System.out.println("Total de vendas por tipo de café:");
        System.out.println("----------------------------------------");
        
        for (Map.Entry<String, Double> entry : vendasPorCafe.entrySet()) {
            System.out.printf("%-15s: R%.2f%n", entry.getKey(), entry.getValue());
        }
        
        double totalGeral = vendasPorCafe.values().stream().mapToDouble(Double::doubleValue).sum();
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL GERAL: R%.2f%n", totalGeral);
    }
}