import org.junit.*;

public class TestEqual {

    @Test
    public void testNumerosIguales() {
        Operaciones ops = new Operaciones();
        // Caso: 5 es igual a 5, se espera true.
        Assert.assertTrue("Se esperaba que 5 sea igual a 5", ops.equal(5, 5));
    }

    @Test
    public void testNumerosDiferentes() {
        Operaciones ops = new Operaciones();
        // Caso: 5 no es igual a 3, se espera false.
        Assert.assertFalse("Se esperaba que 5 no sea igual a 3", ops.equal(5, 3));
    }
}
