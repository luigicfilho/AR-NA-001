public class PedidoManager {

    public void processarPedido(String cliente, String produto, int quantidade, String endereco, String metodoPagamento, String tipoFrete) {
        // SRP VIOLADO: Validação deveria ser responsabilidade de outra classe!
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new RuntimeException("Cliente inválido");
        }
        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        // SRP VIOLADO: Cálculo de preço e imposto misturado com processamento principal
        double precoUnitario = buscarPrecoProduto(produto);
        double valorTotal = precoUnitario * quantidade;
        if (valorTotal > 500) {
            valorTotal *= 0.9;
        }
        valorTotal += calcularImposto(valorTotal, "SP"); // hardcoded — 🔴 SRP novamente

        // DIP VIOLADO: Dependência direta de MySQL — sem injeção, sem abstração!
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

        // SRP VIOLADO: Envio de e-mail hardcoded — responsabilidade de notificação!
        enviarEmail(cliente, "Seu pedido foi processado! Total: R$" + valorTotal);

        // OCP VIOLADO: Switch rígido — não consigo adicionar novo tipo de frete sem modificar este método!
        // ISP VIOLADO: Se houvesse interface, ela estaria inchada — mas aqui nem interface existe!
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

        // SRP VIOLADO: Geração de PDF hardcoded — deveria ser serviço separado!
        gerarPDF(cliente, produto, quantidade, valorTotal, frete);
    }

    private double buscarPrecoProduto(String produto) {
        // SRP VIOLADO: lógica de catálogo de produtos misturada com processamento de pedido
        if ("Notebook".equals(produto)) return 3500.0;
        if ("Mouse".equals(produto)) return 50.0;
        if ("Teclado".equals(produto)) return 120.0;
        return 0.0;
    }

    private double calcularImposto(double valor, String estado) {
        // SRP VIOLADO: cálculo fiscal deveria ser modular e injetável
        if ("SP".equals(estado)) return valor * 0.18;
        if ("RJ".equals(estado)) return valor * 0.20;
        return valor * 0.15; // default
    }

    private void enviarEmail(String destinatario, String mensagem) {
        // SRP VIOLADO: envio de notificação não é responsabilidade do processador de pedido
        System.out.println("[EMAIL] Enviando para " + destinatario + ": " + mensagem);
    }

    private void gerarPDF(String cliente, String produto, int qtd, double total, double frete) {
        // SRP VIOLADO: geração de relatório não pertence aqui
        System.out.println("[PDF] Gerando relatório para " + cliente + " - Produto: " + produto + " - Frete: R$" + frete);
    }
}