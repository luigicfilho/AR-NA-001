// ——— Interfaces ———

interface ValidadorPedido {
    void validar(String cliente, int quantidade);
}

interface CalculadorValor {
    double calcularTotal(String produto, int quantidade);
}

interface RepositorioPedido {
    void salvar(String cliente, String produto, int quantidade, double valorTotal);
}

interface ServicoNotificacao {
    void notificar(String destinatario, String mensagem);
}

interface CalculadorFrete {
    double calcular(String tipoFrete, double valorTotal);
}

interface GeradorRelatorio {
    void gerar(String cliente, String produto, int quantidade, double total, double frete);
}

// ——— Implementações ———

class ValidadorPedidoSimples implements ValidadorPedido {
    @Override
    public void validar(String cliente, int quantidade) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new RuntimeException("Cliente inválido");
        }
        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }
    }
}

class CalculadorValorComDesconto implements CalculadorValor {
    @Override
    public double calcularTotal(String produto, int quantidade) {
        double precoUnitario = buscarPrecoProduto(produto);
        double valorTotal = precoUnitario * quantidade;
        if (valorTotal > 500) {
            valorTotal *= 0.9; // desconto de 10%
        }
        return valorTotal;
    }

    private double buscarPrecoProduto(String produto) {
        if ("Notebook".equals(produto)) return 3500.0;
        if ("Mouse".equals(produto)) return 50.0;
        if ("Teclado".equals(produto)) return 120.0;
        return 0.0;
    }
}

class RepositorioPedidoMySQL implements RepositorioPedido {
    @Override
    public void salvar(String cliente, String produto, int quantidade, double valorTotal) {
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
            throw new RuntimeException("Erro ao salvar pedido", e);
        }
    }
}

class ServicoEmail implements ServicoNotificacao {
    @Override
    public void notificar(String destinatario, String mensagem) {
        System.out.println("[EMAIL] Enviando para " + destinatario + ": " + mensagem);
    }
}

class CalculadorFreteStrategy implements CalculadorFrete {
    @Override
    public double calcular(String tipoFrete, double valorTotal) {
        switch (tipoFrete) {
            case "NORMAL": return 10.0;
            case "EXPRESSO": return 25.0;
            case "GRATIS":
                if (valorTotal < 200) {
                    throw new RuntimeException("Frete grátis só para pedidos acima de R$200");
                }
                return 0.0;
            default:
                throw new RuntimeException("Tipo de frete inválido");
        }
    }
}

class GeradorPDF implements GeradorRelatorio {
    @Override
    public void gerar(String cliente, String produto, int quantidade, double total, double frete) {
        System.out.println("[PDF] Gerando relatório para " + cliente + " - Produto: " + produto + " - Frete: R$" + frete);
    }
}

// ——— Classe Principal (PedidoService) ———
class PedidoService {
    private final ValidadorPedido validador;
    private final CalculadorValor calculadorValor;
    private final RepositorioPedido repositorio;
    private final ServicoNotificacao notificador;
    private final CalculadorFrete calculadorFrete;
    private final GeradorRelatorio geradorRelatorio;

    // Injeção de dependências — DIP
    public PedidoService(
        ValidadorPedido validador,
        CalculadorValor calculadorValor,
        RepositorioPedido repositorio,
        ServicoNotificacao notificador,
        CalculadorFrete calculadorFrete,
        GeradorRelatorio geradorRelatorio
    ) {
        this.validador = validador;
        this.calculadorValor = calculadorValor;
        this.repositorio = repositorio;
        this.notificador = notificador;
        this.calculadorFrete = calculadorFrete;
        this.geradorRelatorio = geradorRelatorio;
    }

    public void processarPedido(String cliente, String produto, int quantidade, String tipoFrete) {
        // SRP: só orquestra — não faz nada por si só
        validador.validar(cliente, quantidade);

        double valorTotal = calculadorValor.calcularTotal(produto, quantidade);
        valorTotal += valorTotal * 0.18; 

        double frete = calculadorFrete.calcular(tipoFrete, valorTotal);

        repositorio.salvar(cliente, produto, quantidade, valorTotal);
        notificador.notificar(cliente, "Seu pedido foi processado! Total: R$" + valorTotal);
        geradorRelatorio.gerar(cliente, produto, quantidade, valorTotal, frete);
    }
}