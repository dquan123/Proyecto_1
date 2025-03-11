import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Clase intérprete, es en esta en donde se evalúa la expresión
public class Interprete {
    public String cadena; //Tiene como atributo la cadena (el código ingresado al programa)

    public Interprete(String cadena) {
        this.cadena = cadena;
    }
    
    //Función que recibe la cadena y devuelve una lista con los operandos y operadores en distintas posiciones
    public ArrayList<String> token(String cadena) {

        ArrayList<String> lista = new ArrayList<>(); //Crea la lista que se devolverá
        
        Pattern pattern = Pattern.compile("[\\wáéíóúüñ]+|[.,!?;:\"'()/¡¿+--*]"); //Genera el patrón que buscará palabras y signos de puntuación usando la expresión regular
        Matcher matcher = pattern.matcher(cadena); //Usa el patrón generado para buscar coincidencias en la cadena

        while (matcher.find()) { //Se repite con cada coincidencia de la expresión regular dentro de la cadena
            lista.add(matcher.group()); //Agrega a la lista cada coincidencia
        }

        return lista; //Devuelve la lista
    }

//Función que evalúa toda la expresión (El programa)
public void evaluar(ArrayList<String> tokens){
    Operaciones a = new Operaciones();
    Stack<String> stack = new Stack<>();
    
    for (String token : tokens) {
        if (token.equals(")")) {
            ArrayList<String> temporal = new ArrayList<>();
            String r;
            
            // Extraer elementos hasta encontrar "("
            do {
                r = stack.pop();
                temporal.add(String.valueOf(r));
            } while (!r.equals("("));

            String operador = temporal.get(temporal.size() - 2);
            temporal.remove(temporal.size() - 1);
            temporal.remove(temporal.size() - 1);

            // Ejecutar operación según el operador
            if (operador.equals("+")) {
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                stack.push(String.valueOf(a.suma(listaEnteros))); // Apilar el resultado
            } else if (operador.equals("*")) {
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                stack.push(String.valueOf(a.multiplicacion(listaEnteros))); // Apilar el resultado
            } else if (operador.equals("-")) {
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                stack.push(String.valueOf(a.resta(listaEnteros))); // Apilar el resultado
            } else if (operador.equals("/")) {
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                stack.push(String.valueOf(a.division(listaEnteros))); // Apilar el resultado
            }
            
        } else {
            stack.push(token);
        }
    }
    
    System.out.println("Resultado final en la pila: " + stack);
}


    
}