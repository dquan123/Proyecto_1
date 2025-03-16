import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestBuscar {

    private Entorno entorno;

    @Before
    public void setUp() {
        entorno = new Entorno();
    }

    /**
     * Prueba para buscar en el HashMap de enteros.
     * Se inserta la clave "prueba" con el valor 7 y se espera que se retorne 7.
     */
    @Test
    public void testBuscarEnteros() {
        entorno.getEnteros().put("prueba", 7);
        Object resultado = entorno.buscar("prueba", entorno.getEnteros());
        assertNotNull("El resultado no debe ser null", resultado);
        assertEquals("El método buscar debe retornar 7 para la clave 'prueba'",
                     7, resultado);
    }

    /**
     * Prueba para buscar en el HashMap de cadenas.
     * Se inserta la clave "prueba1" con el valor "Hola mundo" y se espera que se retorne "Hola mundo".
     */
    @Test
    public void testBuscarCadenas() {
        entorno.getCadenas().put("prueba1", "Hola mundo");
        Object resultado = entorno.buscar("prueba1", entorno.getCadenas());
        assertNotNull("El resultado no debe ser null", resultado);
        assertEquals("El método buscar debe retornar 'Hola mundo' para la clave 'prueba1'",
                     "Hola mundo", resultado);
    }

    /**
     * Prueba para buscar en el HashMap de booleanos.
     * Se inserta la clave "pruebaBool" con el valor true y se espera que se retorne true.
     */
    @Test
    public void testBuscarBooleanos() {
        entorno.getBooleanos().put("pruebaBool", true);
        Object resultado = entorno.buscar("pruebaBool", entorno.getBooleanos());
        assertNotNull("El resultado no debe ser null", resultado);
        assertEquals("El método buscar debe retornar true para la clave 'pruebaBool'",
                     true, resultado);
    }
}
