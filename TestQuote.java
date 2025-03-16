import org.junit.*;
import java.util.ArrayList;
import java.util.List;

public class TestQuote {

    @Test
    public void testQuoteReturnsSameList() {
        Operaciones ops = new Operaciones();
        // Crear una lista con algunos elementos
        List<Integer> inputList = new ArrayList<>();
        inputList.add(1);
        inputList.add(2);
        inputList.add(3);
        
        // Llamar a la función Quote y obtener la lista retornada
        List<Integer> outputList = ops.Quote(inputList);
        
        // Verificar que la lista retornada sea igual a la de entrada
        Assert.assertEquals("La lista retornada por Quote debe ser igual a la lista de entrada", 
                            inputList, outputList);
        
        // Además, se puede verificar que ambas referencias sean la misma instancia
        Assert.assertTrue("La lista retornada debe ser la misma instancia que la lista de entrada", 
                          inputList == outputList);
    }
    
    @Test
    public void testQuoteEmptyList() {
        Operaciones ops = new Operaciones();
        // Crear una lista vacía
        List<Integer> inputList = new ArrayList<>();
        
        // Llamar a Quote y obtener el resultado
        List<Integer> outputList = ops.Quote(inputList);
        
        // Verificar que la lista vacía retornada sea igual a la de entrada
        Assert.assertEquals("La lista retornada por Quote para una lista vacía debe ser vacía", 
                            inputList, outputList);
        
        // Comprobar que se trate de la misma instancia
        Assert.assertTrue("La lista retornada debe ser la misma instancia que la lista de entrada", 
                          inputList == outputList);
    }
}
