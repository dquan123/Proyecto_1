import org.junit.*;
import static org.junit.Assert.*;

public class TestCond {

    private Operaciones op;

    @Before
    public void setUp() {
        op = new Operaciones();
    }
    
    /**
     * Caso 1: Una única cláusula con condición "true".
     * Entrada: { {"true", "Mayor"} } con default "Default"
     * Salida esperada: "Mayor"
     */
    @Test
    public void testSingleClauseTrue() {
        Object[][] clauses = { {"true", "Mayor"} };
        String result = op.cond(clauses, "Default");
        assertEquals("Mayor", result);
    }
    
    /**
     * Caso 2: Múltiples cláusulas; la primera es "false" y la segunda "true".
     * Entrada: { {"false", "No cumple"}, {"true", "Igual"} } con default "Default"
     * Salida esperada: "Igual"
     */
    @Test
    public void testMultipleClausesSecondTrue() {
        Object[][] clauses = { {"false", "No cumple"}, {"true", "Igual"} };
        String result = op.cond(clauses, "Default");
        assertEquals("Igual", result);
    }
    
    /**
     * Caso 3: Ninguna cláusula verdadera, se retorna el valor default.
     * Entrada: { {"false", "A"}, {"false", "B"} } con default "Default"
     * Salida esperada: "Default"
     */
    @Test
    public void testNoClauseTrueDefaultReturned() {
        Object[][] clauses = { {"false", "A"}, {"false", "B"} };
        String result = op.cond(clauses, "Default");
        assertEquals("Default", result);
    }
    
    /**
     * Caso 4: Ninguna cláusula verdadera y default es null.
     * Entrada: { {"false", "X"} } con default null
     * Salida esperada: null
     */
    @Test
    public void testDefaultAsNull() {
        Object[][] clauses = { {"false", "X"} };
        String result = op.cond(clauses, null);
        assertNull(result);
    }
    
    /**
     * Caso 5: Uso de "t" para indicar la cláusula verdadera.
     * Entrada: { {"false", "X"}, {"t", "Ok"} } con default "Default"
     * Salida esperada: "Ok"
     */
    @Test
    public void testUsingTAsTrue() {
        Object[][] clauses = { {"false", "X"}, {"t", "Ok"} };
        String result = op.cond(clauses, "Default");
        assertEquals("Ok", result);
    }

    /**
     * Caso complejo:
     * Se definen cuatro cláusulas:
     * - Primera cláusula: condición "false", resultado "First"
     * - Segunda cláusula: condición "false", resultado "Second"
     * - Tercera cláusula: condición "t" (que debe evaluarse como verdadera), resultado "Third"
     * - Cuarta cláusula: condición "true", resultado "Fourth"
     * 
     * Se espera que el método retorne "Third" (la primera cláusula verdadera).
     */
    @Test
    public void testComplexCond() {
        Object[][] clauses = {
            {"false", "First"},
            {"false", "Second"},
            {"t", "Third"},
            {"true", "Fourth"}
        };
        String result = op.cond(clauses, "Default");
        assertEquals("Third", result);
    }
}
