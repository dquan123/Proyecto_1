import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class TestEvaluar {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        outContent.reset();
    }
    
    /**
     * Caso de prueba 1:
     * Entrada: ["(", "print", "(", "+", "3", "4", ")", ")"]
     * Se espera que se imprima “7”.
     */
    @Test
    public void testEvaluarSuma() {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList("(", "print", "(", "+", "3", "4", ")", ")"));
        Interprete interprete = new Interprete("");
        interprete.evaluar(tokens);
        
        String expectedOutput = "7" + System.lineSeparator();
        assertEquals("La evaluación de la expresión ( print ( + 3 4 ) ) debe imprimir 7",
                     expectedOutput, outContent.toString());
    }
    
    /**
     * Caso de prueba 2:
     * Entrada: ["(", "print", "(", "*", "(", "+", "10", "2", ")", "(", "-", "8", "3", ")", ")", ")"]
     * Se espera que se imprima “60”.
     * 
     * Cálculo:
     *   ( + 10 2 ) => 12  
     *   ( - 8 3 )  => 5  
     *   ( * 12 5 ) => 60
     */
    @Test
    public void testEvaluarMultiplicacionYResta() {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(
            "(", "print", "(", "*", "(", "+", "10", "2", ")", "(", "-", "8", "3", ")", ")", ")"
        ));
        Interprete interprete = new Interprete("");
        interprete.evaluar(tokens);
        
        String expectedOutput = "60" + System.lineSeparator();
        assertEquals("La evaluación de la expresión ( print ( * ( + 10 2 ) ( - 8 3 ) ) ) debe imprimir 60",
                     expectedOutput, outContent.toString());
    }
    
    /**
     * Caso de prueba 3:
     * Entrada: ["(", "print", "(", "/", "(", "-", "(", "*", "6", "7", ")", "10", ")", "(", "+", "3", "5", ")", ")", ")"]
     * Se espera que se imprima “4”.
     * 
     * Cálculo:
     *   ( * 6 7 )   => 42  
     *   ( - 42 10 ) => 32  
     *   ( + 3 5 )   => 8  
     *   ( / 32 8 )  => 4
     */
    @Test
    public void testEvaluarDivisionConRestaYMultiplicacion() {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(
            "(", "print", "(", "/", "(", "-", "(", "*", "6", "7", ")", "10", ")", "(", "+", "3", "5", ")", ")", ")"
        ));
        Interprete interprete = new Interprete("");
        interprete.evaluar(tokens);
        
        String expectedOutput = "4" + System.lineSeparator();
        assertEquals("La evaluación de la expresión ( print ( / ( - ( * 6 7 ) 10 ) ( + 3 5 ) ) ) debe imprimir 4",
                     expectedOutput, outContent.toString());
    }
}
