import org.junit.*;
import java.util.ArrayList;

public class TestSuma {

    @Test
    public void testSuma() {
        Operaciones ops = new Operaciones();

        // Caso 1: Lista vacía
        ArrayList<Integer> listaVacia = new ArrayList<>();
        Assert.assertEquals("La suma de una lista vacía debería ser 0",
                            0, ops.suma(listaVacia));

        // Caso 2: Lista con un solo elemento
        ArrayList<Integer> listaUnElemento = new ArrayList<>();
        listaUnElemento.add(5);
        Assert.assertEquals("La suma de una lista con un solo elemento debería ser ese elemento",
                            5, ops.suma(listaUnElemento));

        // Caso 3: Lista con múltiples elementos
        ArrayList<Integer> listaMultiples = new ArrayList<>();
        listaMultiples.add(1);
        listaMultiples.add(2);
        listaMultiples.add(4);
        Assert.assertEquals("La suma de [1, 2, 3] debería ser 6",
                            7, ops.suma(listaMultiples));
    }
}
