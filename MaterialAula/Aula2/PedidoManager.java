public class PedidoManager {

    public void processarPedido(String cliente, String produto, int quantidade, String endereco, String metodoPagamento, String tipoFrete) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new RuntimeException("Cliente inválido");
        }
        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        double precoUnitario = buscarPrecoProduto(produto);
        double valorTotal = precoUnitario * quantidade;
        if (valorTotal > 500) {
            valorTotal *= 0.9; 
        }
        valorTotal += calcularImposto(valorTotal, "SP");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "user", "pass");
            String sql = "INSERT INTO pedidos (cliente, produto, quantidade, valor_total) VALUES (?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente);
            stmt.setString(2, produto);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, valorTotal);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar pedido");
        }

        enviarEmail(cliente, "Seu pedido foi processado! Total: R$" + valorTotal);

        double frete = 0;
        switch (tipoFrete) {
            case "NORMAL":
                frete = 10.0;
                break;
            case "EXPRESSO":
                frete = 25.0;
                break;
            case "GRATIS":
                frete = 0.0;
                if (valorTotal < 200) {
                    throw new RuntimeException("Frete grátis só para pedidos acima de R$200");
                }
                break;
            default:
                throw new RuntimeException("Tipo de frete inválido");
        }

        gerarPDF(cliente, produto, quantidade, valorTotal, frete);
    }

    private double buscarPrecoProduto(String produto) {
        // Simulação de banco ou API
        if ("Notebook".equals(produto)) return 3500.0;
        if ("Mouse".equals(produto)) return 50.0;
        if ("Teclado".equals(produto)) return 120.0;
        return 0.0;
    }

    private double calcularImposto(double valor, String estado) {
        if ("SP".equals(estado)) return valor * 0.18;
        if ("RJ".equals(estado)) return valor * 0.20;
        return valor * 0.15;
    }

    private void enviarEmail(String destinatario, String mensagem) {
        // Simulação envio de Email
        System.out.println("[EMAIL] Enviando para " + destinatario + ": " + mensagem);
    }

    private void gerarPDF(String cliente, String produto, int qtd, double total, double frete) {
        // Simulação geração de PDF
        System.out.println("[PDF] Gerando relatório para " + cliente + " - Produto: " + produto + " - Frete: R$" + frete);
    }
}