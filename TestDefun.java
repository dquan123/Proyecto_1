import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class TestDefun {

    private Operaciones op;

    @Before
    public void setUp() {
        op = new Operaciones();
    }
    
    @Test
    public void testDefunSimple() {
        // Definir una función "suma" que suma dos números.
        List<String> params = Arrays.asList("x", "y");
        List<String> body = Arrays.asList("(+ x y)");  // La forma literal del cuerpo (puede ajustarse a tu sintaxis)
        
        String defunResult = op.defun("suma", params, body);
        assertEquals("suma", defunResult);
        
        // Verificar que la función "suma" se almacenó en el entorno.
        FuncionLisp f = op.getEntorno().getFunciones().get("suma");
        assertNotNull("La función 'suma' debe estar definida", f);
        assertEquals("Los parámetros no coinciden", params, f.getParametros());
        assertEquals("El cuerpo no coincide", body, f.getCuerpo());
    }
    
    @Test
    public void testDefunOverwrite() {
        // Definir inicialmente una función "doble".
        List<String> params1 = Arrays.asList("x");
        List<String> body1 = Arrays.asList("(* x 2)");
        String result1 = op.defun("doble", params1, body1);
        assertEquals("doble", result1);
        
        FuncionLisp f1 = op.getEntorno().getFunciones().get("doble");
        assertNotNull("La función 'doble' debe estar definida", f1);
        assertEquals("Los parámetros iniciales no coinciden", params1, f1.getParametros());
        assertEquals("El cuerpo inicial no coincide", body1, f1.getCuerpo());
        
        // Sobreescribir la función "doble" con nueva definición.
        List<String> params2 = Arrays.asList("x", "y");
        List<String> body2 = Arrays.asList("(+ x y)");
        String result2 = op.defun("doble", params2, body2);
        assertEquals("doble", result2);
        
        FuncionLisp f2 = op.getEntorno().getFunciones().get("doble");
        assertNotNull("La función 'doble' debe estar definida tras sobreescribir", f2);
        // La nueva definición debe sobreescribir la anterior.
        assertEquals("Los nuevos parámetros no coinciden", params2, f2.getParametros());
        assertEquals("El nuevo cuerpo no coincide", body2, f2.getCuerpo());
    }
}
