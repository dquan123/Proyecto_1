import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestToken {

    @Test
    public void testTokenSimple() {
        String input = "hola mundo";
        Interprete interprete = new Interprete(input);
        ArrayList<String> tokens = interprete.token(interprete.cadena);
        // Se espera separar las dos palabras
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("hola", "mundo"));
        assertEquals("La función token debe separar las palabras correctamente.", expected, tokens);
    }
    
    @Test
    public void testTokenWithPunctuation() {
        String input = "saludos, ¿cómo estás?";
        Interprete interprete = new Interprete(input);
        ArrayList<String> tokens = interprete.token(interprete.cadena);
        // Se espera que la expresión se divida en palabras y signos de puntuación:
        // "saludos", ",", "¿", "cómo", "estás", "?"
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("saludos", ",", "¿", "cómo", "estás", "?"));
        assertEquals("La función token debe separar correctamente la puntuación y las palabras.", expected, tokens);
    }
    
    @Test
    public void testTokenComplex() {
        String input = "( print + 1 22 )";
        Interprete interprete = new Interprete(input);
        ArrayList<String> tokens = interprete.token(interprete.cadena);
        // Se espera que se tokenice la expresión completa:
        // "(", "print", "+", "1", "2", ")"
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("(", "print", "+", "1", "22", ")"));
        assertEquals("La función token debe tokenizar correctamente una expresión completa.", expected, tokens);
    }
}
