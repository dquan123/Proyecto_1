import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\dquan\\OneDrive\\Documentos\\Diego Quan\\UVG\\Ciclo 3\\Algoritmos y Estructura de datos\\Proyecto 1 Interprete\\Proyecto_1\\Codigo.txt";
        Scanner scanner = new Scanner(System.in);
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String codigo = br.readLine().trim(); 

            if (codigo.isEmpty()) {
                System.out.println("El archivo está vacío o contiene una línea vacía.");
                return;
            }

            Interprete interprete = new Interprete(codigo);
            ArrayList<String> tokens = interprete.token(codigo);

            System.out.println(tokens);
            System.out.println("\n");

            interprete.evaluar(tokens);


        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la evaluación: " + e.getMessage());
        }
    }
}