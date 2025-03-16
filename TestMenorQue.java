import org.junit.*;

public class TestMenorQue {

    @Test
    public void testMenorQue() {
        Operaciones ops = new Operaciones();
        
        // Caso 1: 3 es menor que 5, se espera true.
        Assert.assertTrue("Se esperaba que 3 sea menor que 5", ops.menorQue(3, 4));
        
        // Caso 2: 5 no es menor que 3, se espera false.
        Assert.assertFalse("Se esperaba que 5 no sea menor que 3", ops.menorQue(5, 2));
        
        // Caso 3: 4 no es menor que 4, se espera false.
        Assert.assertFalse("Se esperaba que 4 no sea menor que 4", ops.menorQue(4, 4));
    }
}
