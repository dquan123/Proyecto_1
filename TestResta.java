import org.junit.*;
import java.util.ArrayList;

public class TestResta {

    @Test
    public void testResta() {
        Operaciones ops = new Operaciones();

        // Caso 1: Lista con un solo elemento
        ArrayList<Integer> listaUnElemento = new ArrayList<>();
        listaUnElemento.add(10);
        Assert.assertEquals("La resta de una lista con un solo elemento debería ser ese elemento",
                            10, ops.resta(listaUnElemento));

        // Caso 2: Lista con dos elementos
        ArrayList<Integer> listaDosElementos = new ArrayList<>();
        listaDosElementos.add(10);
        listaDosElementos.add(2);
        Assert.assertEquals("La resta de [10, 3] debería ser 7",
                            8, ops.resta(listaDosElementos));

        // Caso 3: Lista con tres elementos
        ArrayList<Integer> listaTresElementos = new ArrayList<>();
        listaTresElementos.add(20);
        listaTresElementos.add(5);
        listaTresElementos.add(3);
        Assert.assertEquals("La resta de [20, 5, 3] debería ser 12",
                            12, ops.resta(listaTresElementos));
    }
}
