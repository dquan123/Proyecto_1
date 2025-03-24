import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String ruta = "Codigo.txt";
        Scanner scanner = new Scanner(System.in);
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");  // Agrega cada línea y un salto de línea
            }
            String codigo = sb.toString().trim(); // Código completo
        
            if (codigo.isEmpty()) {
                System.out.println("El archivo está vacío o contiene solo líneas vacías.");
                return;
            }
        
            ArrayList<String> expresiones = separarExpresiones(codigo);
            Operaciones op = new Operaciones();  // Mantener el mismo entorno de variables
    
            for (String expr : expresiones) {
                if (expr.isEmpty()) continue;  // Ignorar expresiones vacías
                Interprete interprete = new Interprete(expr);
                ArrayList<String> tokens = interprete.token(expr);
                interprete.evaluar(tokens, op); // Se pasa la misma instancia de `Operaciones`
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la evaluación: " + e.getMessage());
        }
        
        scanner.close();
    }
    private static ArrayList<String> separarExpresiones(String codigo) {
        ArrayList<String> expresiones = new ArrayList<>();
        int len = codigo.length();
        int i = 0;
        while (i < len) {
            // Saltar espacios y saltos de línea
            while (i < len && Character.isWhitespace(codigo.charAt(i))) {
                i++;
            }
            if (i >= len) break;
    
            // Si la expresión comienza con '(', buscar el paréntesis que cierra la expresión
            if (codigo.charAt(i) == '(') {
                int inicio = i;
                int count = 0;
                while (i < len) {
                    char c = codigo.charAt(i);
                    if (c == '(') count++;
                    else if (c == ')') count--;
                    i++;
                    if (count == 0) break;  // Se encontró el cierre de la expresión
                }
                expresiones.add(codigo.substring(inicio, i).trim());
            } else {
                // Para expresiones que no comienzan con '(', buscar el siguiente salto de línea
                int inicio = i;
                while (i < len && codigo.charAt(i) != '\n') {
                    i++;
                }
                expresiones.add(codigo.substring(inicio, i).trim());
            }
        }
        return expresiones;
    }
}