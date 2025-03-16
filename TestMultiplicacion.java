import org.junit.*;
import java.util.ArrayList;

public class TestMultiplicacion {

    @Test
    public void testMultiplicacionListaVacia() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaVacia = new ArrayList<>();
        // Al no haber elementos, se retorna el valor inicial 1.
        Assert.assertEquals("La multiplicación de una lista vacía debería ser 1",
                            1, ops.multiplicacion(listaVacia));
    }
    
    @Test
    public void testMultiplicacionUnElemento() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaUnElemento = new ArrayList<>();
        listaUnElemento.add(5);
        // Si hay un solo elemento, la multiplicación es ese mismo número.
        Assert.assertEquals("La multiplicación de una lista con un solo elemento debería ser ese elemento",
                            5, ops.multiplicacion(listaUnElemento));
    }
    
    @Test
    public void testMultiplicacionMultiplesElementos() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaMultiples = new ArrayList<>();
        listaMultiples.add(2);
        listaMultiples.add(3);
        listaMultiples.add(4);
        // Se espera: 2 * 3 * 4 = 24
        Assert.assertEquals("La multiplicación de [2, 3, 4] debería ser 24",
                            24, ops.multiplicacion(listaMultiples));
    }
}
