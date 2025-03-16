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
        
        Pattern pattern = Pattern.compile("\"[^\"]*\"|[\\wáéíóúüñ]+|[.,!?;:\"'()/¡¿+\\-=*]");
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
    boolean printed = false; // Bandera para saber si se ejecutó un print
    
    for (int i = 0; i < tokens.size(); i++) {
        String token = tokens.get(i);
        
        if (token.equals("(") && (i + 1 < tokens.size() && tokens.get(i + 1).equals("quote"))) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            // Recorrer desde el token actual (i) hasta que se balanceen los paréntesis.
            for (int j = i; j < tokens.size(); j++) {
                String t = tokens.get(j);
                if (t.equals("(")) {
                    count++;
                } else if (t.equals(")")) {
                    count--;
                }
                sb.append(t).append(" ");
                if (count == 0) {
                    // Actualizamos el índice para saltarnos los tokens ya procesados.
                    i = j;
                    break;
                }
            }
            // La subexpresión completa quedaría, por ejemplo: "( quote (+ 1 2 3) )"
            String subexpr = sb.toString().trim();
            // Quitar el paréntesis de apertura y cierre:
            if (subexpr.startsWith("(") && subexpr.endsWith(")")) {
                subexpr = subexpr.substring(1, subexpr.length() - 1).trim();
            }
            // Se espera que subexpr ahora sea: "quote (+ 1 2 3)"
            // Quitar el operador "quote":
            if (subexpr.startsWith("quote")) {
                subexpr = subexpr.substring("quote".length()).trim();
            }
            // Ahora, subexpr debería ser la parte literal: "(+ 1 2 3)"
            stack.push(subexpr);
            continue; // Saltamos el procesamiento normal para estos tokens.
        } else if (token.equals(")")) {
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
            } else if (operador.equals("print")) {
                Collections.reverse(temporal);
                a.print(temporal.toString()); //Mostrar el resultado
                printed = true;
            } else if (operador.equals("<")) {
                // Se esperan exactamente dos operandos
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                if (listaEnteros.size() >= 2) {
                    boolean resultado = a.menorQue(listaEnteros.get(0), listaEnteros.get(1));
                    stack.push(String.valueOf(resultado));
                }
            } else if (operador.equals(">")) {
                // Se esperan exactamente dos operandos
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                if (listaEnteros.size() >= 2) {
                    boolean resultado = a.mayorQue(listaEnteros.get(0), listaEnteros.get(1));
                    stack.push(String.valueOf(resultado));
                }
            } else if (operador.equals("atom")) {
                Collections.reverse(temporal);
                Object argumento;
                // Si solo hay un elemento, lo usamos directamente; si hay más, usamos la lista
                if (temporal.size() == 1) {
                    argumento = temporal.get(0);
                } else {
                    argumento = temporal;
                }
                Boolean res = a.atom(argumento);
                stack.push(String.valueOf(res));
            } else if (operador.equals("list")) {
                Collections.reverse(temporal);
                Object argumento;
                if (temporal.size() == 1) {
                    argumento = temporal.get(0);
                } else {
                    argumento = temporal;
                }
                Boolean res = a.list(argumento);
                stack.push(String.valueOf(res));
            } else if (operador.equals("equal") || operador.equals("=")) {
                // Se esperan dos operandos para comparar
                ArrayList<Integer> listaEnteros = new ArrayList<>();
                for (String s : temporal) {
                    listaEnteros.add(Integer.parseInt(s));
                }
                Collections.reverse(listaEnteros);
                if (listaEnteros.size() >= 2) {
                    boolean resultado = a.equal(listaEnteros.get(0), listaEnteros.get(1));
                    stack.push(String.valueOf(resultado));
                }
            } else if (operador.equals("setq")) {
                // No evaluamos los argumentos: en setq, el primer argumento es el nombre de la variable
                // y el segundo es el valor (literal o evaluado según la implementación deseada).
                Collections.reverse(temporal);
                if (temporal.size() >= 2) {
                     String variable = temporal.get(0);
                     String valor = temporal.get(1);
                     a.setq(variable, valor);
                     // Lisp devuelve el valor asignado, por eso lo apilamos.
                     stack.push(valor);
                }
            }
            
            
        } else {
            // Antes de simplemente empujar el token, se verifica si es una variable definida.
            String valorVariable = a.buscarVariable(token);
            if (valorVariable != null) {
                stack.push(valorVariable);
            } else {
                stack.push(token);
            }
        }
    }
    if (printed) {
        //
    }    
    
}

// Método para obtener la subexpresión literal a partir de los tokens sin evaluarlos
private String obtenerSubexpresionLiteral(Stack<String> stack) {
    // Asumamos que el token "(" ya fue consumido.
    StringBuilder literal = new StringBuilder();
    int count = 1; // ya consumimos un "("
    while (!stack.isEmpty() && count > 0) {
        String token = stack.pop();
        if (token.equals("(")) {
            count++;
        } else if (token.equals(")")) {
            count--;
        }
        literal.insert(0, token + " ");
    }
    return literal.toString().trim();
}



    
}