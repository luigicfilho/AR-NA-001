import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositorioUsuarioTest {

    private RepositorioUsuario repositorio;
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USUARIO = "sa";
    private static final String SENHA = "";

    @BeforeEach
    void setUp() {
        repositorio = new RepositorioUsuario(URL, USUARIO, SENHA);
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM usuarios");
        }
    }

    @Test
    void deveSalvarEListarUsuario() {
        repositorio.salvar("João");
        List<String> usuarios = repositorio.listarTodos();
        assertEquals(1, usuarios.size());
        assertTrue(usuarios.contains("João"));
    }
}