import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void deveSomarDoisNumeros() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(2, 3);
        assertEquals(5, resultado, "2 + 3 deve ser 5");
    }

    @Test
    void deveLancarExcecaoQuandoDividirPorZero() {
        Calculadora calc = new Calculadora();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, 0);
        });
        assertEquals("Divisão por zero não é permitida.", exception.getMessage());
    }
}