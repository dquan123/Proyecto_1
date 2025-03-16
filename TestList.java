import org.junit.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestList {

    @Test
    public void testListWithArrayList() {
        Operaciones ops = new Operaciones();
        ArrayList<String> lista = new ArrayList<>();
        // Al pasar un ArrayList, la función debe retornar true.
        Assert.assertTrue("Se esperaba que un ArrayList sea considerado lista", ops.list(lista));
    }

    @Test
    public void testListWithOtherList() {
        Operaciones ops = new Operaciones();
        List<String> lista = new LinkedList<>();
        // La implementación verifica específicamente si el objeto es un ArrayList,
        // por lo que un LinkedList debe retornar false.
        Assert.assertFalse("Se esperaba que un LinkedList no sea considerado lista", ops.list(lista));
    }

    @Test
    public void testListWithNonList() {
        Operaciones ops = new Operaciones();
        // Al pasar un objeto que no es una lista (por ejemplo, una cadena), se espera false.
        Assert.assertFalse("Se esperaba que un objeto no lista no sea considerado lista", ops.list("hola"));
    }
}
