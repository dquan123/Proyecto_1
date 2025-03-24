import org.junit.*;
import static org.junit.Assert.*;

public class TestCond {

    private Operaciones op;
    
    @Before
    public void setUp() {
        op = new Operaciones();
    }
    
    /**
     * Caso 1: Una única cláusula verdadera.
     * Entrada Lisp: (print (cond ((true "Mayor"))))
     * Se espera: "Mayor"
     */
    @Test
    public void testIndividual() {
        Object[][] clauses = { { true, "Mayor" } };
        String result = op.cond(clauses, "Default");
        assertEquals("Mayor", result);
    }
    
    /**
     * Caso 2: Dos cláusulas, la primera falsa y la segunda con default "t".
     * Entrada Lisp: (print (cond ((false "No cumple") (t "Default"))))
     * Se espera: "Default"
     */
    @Test
    public void testDefault() {
        Object[][] clauses = { { false, "No cumple" }, { true, "Default" } };
        String result = op.cond(clauses, "Fallback");
        assertEquals("Default", result);
    }
    
    /**
     * Caso 3: Tres cláusulas, donde la segunda es la primera verdadera.
     * Entrada Lisp: (print (cond ((false "A") ((false "B")) (t "C")))
     * Se espera: se retorna la primera verdadera; aquí simulamos que la segunda es verdadera.
     */
    @Test
    public void testMultiples() {
        Object[][] clauses = { { false, "A" }, { true, "B" }, { true, "C" } };
        String result = op.cond(clauses, "Fallback");
        assertEquals("B", result);
    }
    
    /**
     * Caso 4: Ninguna cláusula verdadera, se retorna el default.
     * Entrada Lisp: (print (cond ((false "A") ((false "B")) (else "Default"))))
     * Se espera: "Default"
     */
    @Test
    public void TestFalse() {
        Object[][] clauses = { { false, "A" }, { false, "B" } };
        String result = op.cond(clauses, "Default");
        assertEquals("Default", result);
    }
    
    /**
     * Caso 5: Simulación de una condición compleja.
     * Aunque el método cond espera booleans, simulamos una condición
     * derivada de una operación (por ejemplo, (> 5 3) que sería true).
     * Entrada Lisp equivalente: (print (cond (((> 5 3)) "Mayor")))
     * Se espera: "Mayor"
     */
    @Test
    public void testComplexClauseSimulated() {
        // Simulamos que la expresión (> 5 3) ya evaluó a true.
        Object[][] clauses = { { true, "Mayor" } };
        String result = op.cond(clauses, "Default");
        assertEquals("Mayor", result);
    }
}
