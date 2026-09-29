import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {

    private final String url;
    private final String usuario;
    private final String senha;

    public RepositorioUsuario(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
        inicializarBanco();
    }

    private void inicializarBanco() {
        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100) NOT NULL)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar banco", e);
        }
    }

    public void salvar(String nome) {
        String sql = "INSERT INTO usuarios (nome) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }

    public List<String> listarTodos() {
        List<String> usuarios = new ArrayList<>();
        String sql = "SELECT nome FROM usuarios";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return usuarios;
    }
}