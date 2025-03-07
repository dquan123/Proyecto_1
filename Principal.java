import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\dquan\\OneDrive\\Documentos\\Diego Quan\\UVG\\Ciclo 3\\Algoritmos y Estructura de datos\\Proyecto 1 Interprete\\Proyecto_1\\Codigo.txt";
        Scanner scanner = new Scanner(System.in);
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String codigo = br.readLine(); 

            if (codigo.isEmpty()) {
                System.out.println("El archivo está vacío o contiene una línea vacía.");
                return;
            }

            System.out.println(codigo);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la evaluación: " + e.getMessage());
        }
    }
}