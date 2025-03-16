import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestPrint {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrint() {
        Operaciones ops = new Operaciones();
        String mensaje = "Hola Mundo!";
        ops.print(mensaje);
        // System.out.println agrega un salto de línea al final.
        String expectedOutput = mensaje + System.lineSeparator();
        Assert.assertEquals("La salida del método print debería ser la cadena con un salto de línea",
                            expectedOutput, outContent.toString());
    }
}
