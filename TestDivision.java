import org.junit.*;
import java.util.ArrayList;

public class TestDivision {

    @Test
    public void testDivisionUnElemento() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaUnElemento = new ArrayList<>();
        listaUnElemento.add(10);
        // Si hay un solo elemento, la división retorna ese mismo número.
        Assert.assertEquals("La división de una lista con un solo elemento debería ser ese elemento",
                            10, ops.division(listaUnElemento));
    }
    
    @Test
    public void testDivisionDosElementos() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaDosElementos = new ArrayList<>();
        listaDosElementos.add(20);
        listaDosElementos.add(4);
        // Se espera: 20 / 4 = 5
        Assert.assertEquals("La división de [20, 4] debería ser 5",
                            5, ops.division(listaDosElementos));
    }
    
    @Test
    public void testDivisionMultiplesElementos() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaMultiplesElementos = new ArrayList<>();
        listaMultiplesElementos.add(100);
        listaMultiplesElementos.add(5);
        listaMultiplesElementos.add(2);
        // Se espera: 100 / 5 = 20, luego 20 / 2 = 10
        Assert.assertEquals("La división de [100, 5, 2] debería ser 10",
                            10, ops.division(listaMultiplesElementos));
    }
    
    @Test
    public void testDivisionConTruncamiento() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> listaTruncamiento = new ArrayList<>();
        listaTruncamiento.add(7);
        listaTruncamiento.add(2);
        // En división entera: 7 / 2 = 3 (se trunca la parte decimal)
        Assert.assertEquals("La división de [7, 2] debería ser 3 (por truncamiento de la división entera)",
                            3, ops.division(listaTruncamiento));
    }
}
