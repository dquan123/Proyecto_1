import org.junit.*;
import java.util.ArrayList;

public class TestAtom {

    @Test
    public void testAtomWithNull() {
        Operaciones ops = new Operaciones();
        // Se espera que null se considere átomo, ya que la función retorna true para null.
        Assert.assertTrue("Se esperaba que null sea considerado átomo", ops.atom(null));
    }

    @Test
    public void testAtomWithString() {
        Operaciones ops = new Operaciones();
        // Una cadena no es una lista, por lo que se espera true.
        Assert.assertTrue("Se esperaba que una cadena sea considerada átomo", ops.atom("hola"));
    }

    @Test
    public void testAtomWithArrayList() {
        Operaciones ops = new Operaciones();
        ArrayList<Integer> lista = new ArrayList<>();
        // Al pasar una lista (ArrayList), se espera false, ya que no es átomo.
        Assert.assertFalse("Se esperaba que una lista no sea considerada átomo", ops.atom(lista));
    }
}
