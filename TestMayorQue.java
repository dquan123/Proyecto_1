import org.junit.*;

public class TestMayorQue {

    @Test
    public void testMayorQue() {
        Operaciones ops = new Operaciones();
        
        // Caso 1: 5 es mayor que 3, se espera true.
        Assert.assertTrue("Se esperaba que 5 sea mayor que 3", ops.mayorQue(5, 3));
        
        // Caso 2: 3 no es mayor que 5, se espera false.
        Assert.assertFalse("Se esperaba que 3 no sea mayor que 5", ops.mayorQue(3, 5));
        
        // Caso 3: 4 no es mayor que 4, se espera false.
        Assert.assertFalse("Se esperaba que 4 no sea mayor que 4", ops.mayorQue(5, 5));
    }
}
