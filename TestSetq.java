import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestSetq {

    @Test
    public void testSetqBooleanTrue() {
        Operaciones ops = new Operaciones();
        ops.setq("varTrue", "true");
        
        // Se asume que existe un método getEntorno() en Operaciones
        HashMap<String, Boolean> booleanos = ops.getEntorno().getBooleanos();
        assertNotNull("El mapa de booleanos no debe ser nulo", booleanos);
        assertTrue("El valor de 'varTrue' debe ser true", booleanos.get("varTrue"));
    }
    
    @Test
    public void testSetqBooleanFalse() {
        Operaciones ops = new Operaciones();
        ops.setq("varFalse", "false");
        
        HashMap<String, Boolean> booleanos = ops.getEntorno().getBooleanos();
        assertNotNull("El mapa de booleanos no debe ser nulo", booleanos);
        assertFalse("El valor de 'varFalse' debe ser false", booleanos.get("varFalse"));
    }
    
    @Test
    public void testSetqInteger() {
        Operaciones ops = new Operaciones();
        ops.setq("varInt", "123");
        
        HashMap<String, Integer> enteros = ops.getEntorno().getEnteros();
        assertNotNull("El mapa de enteros no debe ser nulo", enteros);
        assertEquals("El valor de 'varInt' debe ser 123", 123, (int) enteros.get("varInt"));
    }
    
    @Test
    public void testSetqString() {
        Operaciones ops = new Operaciones();
        ops.setq("varString", "hola");
        
        HashMap<String, String> cadenas = ops.getEntorno().getCadenas();
        assertNotNull("El mapa de cadenas no debe ser nulo", cadenas);
        assertEquals("El valor de 'varString' debe ser 'hola'", "hola", cadenas.get("varString"));
    }
}
