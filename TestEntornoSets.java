import org.junit.*;
import static org.junit.Assert.*;

public class TestEntornoSets {

    private Entorno entorno;

    @Before
    public void setUp() {
        entorno = new Entorno();
    }

    @Test
    public void testSetEnteros() {
        // Agregamos un valor al mapa de enteros
        entorno.getEnteros().put("numero", 42);
        assertEquals("El valor asociado a 'numero' en enteros debe ser 42", 
                     (Integer)42, entorno.getEnteros().get("numero"));
    }

    @Test
    public void testSetCadenas() {
        // Agregamos un valor al mapa de cadenas
        entorno.getCadenas().put("saludo", "hola");
        assertEquals("El valor asociado a 'saludo' en cadenas debe ser 'hola'", 
                     "hola", entorno.getCadenas().get("saludo"));
    }

    @Test
    public void testSetBooleanos() {
        // Agregamos un valor al mapa de booleanos
        entorno.getBooleanos().put("flag", true);
        assertEquals("El valor asociado a 'flag' en booleanos debe ser true", 
                     true, entorno.getBooleanos().get("flag"));
    }
}
