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
        
        Pattern pattern = Pattern.compile("\"[^\"]*\"|[\\wáéíóúüñ]+|[.,!?;:\"'()/¡¿+\\-=<>*]");
        Matcher matcher = pattern.matcher(cadena); //Usa el patrón generado para buscar coincidencias en la cadena

        while (matcher.find()) { //Se repite con cada coincidencia de la expresión regular dentro de la cadena
            lista.add(matcher.group()); //Agrega a la lista cada coincidencia
        }

        return lista; //Devuelve la lista
    }

//Función que evalúa toda la expresión (El programa)
public void evaluar(ArrayList<String> tokens, Operaciones a) {
    if (tokens.contains("cond")) {  // Verifica si "cond" está en cualquier parte de la expresión
        evaluarCond(tokens, a);
    } else {
        evaluarNormal(tokens, a);
    }
}

private void evaluarNormal(ArrayList<String> tokens, Operaciones a) {
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
            }else if (operador.equals("defun")) {
                Collections.reverse(temporal);
                // Extraer el nombre de la función (el primer token)
                String functionName = temporal.get(0);
                temporal.remove(0);
                // Extraer la lista de parámetros (entre paréntesis)
                ArrayList<String> paramTokens = new ArrayList<>();
                if (!temporal.isEmpty() && temporal.get(0).equals("(")) {
                    temporal.remove(0); // Quitar el "("
                    while (!temporal.isEmpty() && !temporal.get(0).equals(")")) {
                        paramTokens.add(temporal.get(0));
                        temporal.remove(0);
                    }
                    if (!temporal.isEmpty() && temporal.get(0).equals(")")) {
                        temporal.remove(0); // Quitar el ")"
                    }
                }
                // Lo que queda en 'temporal' es el cuerpo de la función (lista de tokens)
                ArrayList<String> bodyTokens = new ArrayList<>(temporal);
                // Llamar a defun en Operaciones y obtener el nombre de la función definida
                String defunResult = a.defun(functionName, paramTokens, bodyTokens);
                stack.push(defunResult);
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


// Método auxiliar: Extrae cada cláusula (con sus paréntesis exteriores) de un bloque cond.
// El bloque recibido debe tener la forma: "cond <cláusula1> <cláusula2> ..." (sin los paréntesis exteriores).
private ArrayList<String> parseClausesFromBlock(String block) {
    ArrayList<String> clauseStrings = new ArrayList<>();
    int len = block.length();
    int i = 0;
    while (i < len) {
        // Saltar espacios
        while (i < len && Character.isWhitespace(block.charAt(i))) i++;
        if (i >= len) break;
        if (block.charAt(i) == '(') {
            int start = i;
            int count = 0;
            while (i < len) {
                char c = block.charAt(i);
                if (c == '(') count++;
                else if (c == ')') count--;
                i++;
                if (count == 0) break;
            }
            String clause = block.substring(start, i).trim();
            clauseStrings.add(clause);
        } else {
            i++;
        }
    }
    return clauseStrings;
}

// Método auxiliar para evaluar una subexpresión condicional y devolver "true" o "false" como String.
// Soporta comparaciones básicas: >, < y equal.
private boolean evaluarSubexpresion(ArrayList<String> tokens, Operaciones a) {
    if (tokens.size() < 3) return false;  // Si hay menos de 3 elementos, no es una comparación válida

    String operador = tokens.get(0);
    String op1Str = tokens.get(1);
    String op2Str = tokens.get(2);

    if (!op1Str.matches("-?\\d+") || !op2Str.matches("-?\\d+")) {
        throw new RuntimeException("Los operandos de '" + operador + "' deben ser números, pero se encontraron: " + op1Str + " y " + op2Str);
    }    int op1 = Integer.parseInt(tokens.get(1));
    int op2 = Integer.parseInt(tokens.get(2));

    switch (operador) {
        case ">": return op1 > op2;
        case "<": return op1 < op2;
        case "equal": return op1 == op2;
        case "=": return op1 == op2;
        default: return false;  // Si no reconoce el operador, retorna falso
    }
}

// Método de evaluación especial para el operador cond.
// Se extrae el bloque cond desde la cadena original, se retira la palabra "cond" y los paréntesis exteriores,
// y luego se parsean las cláusulas. Cada cláusula debe tener la forma: ((<condición-expression>) <resultado>)
// donde la condición puede ser una expresión (p.ej., (> 5 3)) o un literal ("true", "false", "t", "else").
private void evaluarCond(ArrayList<String> tokens, Operaciones a) {
    // Extraer el bloque (cond ...)
    int condStart = cadena.indexOf("(cond");
    if (condStart == -1) {
        evaluarNormal(tokens, a);
        return;
    }
    int condEnd = findMatchingParen(cadena, condStart);
    if (condEnd == -1) {
        throw new RuntimeException("No se encontró el cierre del bloque cond.");
    }

    // Extraer el contenido de cond sin los paréntesis exteriores
    String condBlock = cadena.substring(condStart, condEnd + 1).trim();
    if (condBlock.startsWith("(") && condBlock.endsWith(")")){
        condBlock = condBlock.substring(1, condBlock.length() - 1).trim();
    }
    if (condBlock.startsWith("cond")){
        condBlock = condBlock.substring(4).trim();
    }
    // Extraer cláusulas del cond
    ArrayList<String> clauseStrings = parseClausesFromBlock(condBlock);
    String defaultResult = null;

    for (String clauseStr : clauseStrings) {
        String clauseContent = clauseStr;
        if (clauseContent.startsWith("(") && clauseContent.endsWith(")"))
            clauseContent = clauseContent.substring(1, clauseContent.length() - 1).trim();

        ArrayList<String> clauseTokens = token(clauseContent);
        if (clauseTokens.size() < 2) continue;

        boolean condition = false;
        String resToken = clauseTokens.get(clauseTokens.size() - 1); // Último elemento es el resultado

        // Manejo especial para `t` y `else`
        if (clauseTokens.get(0).equalsIgnoreCase("t") || clauseTokens.get(0).equalsIgnoreCase("else")) {
            defaultResult = resToken;
            continue;
        }

        // Extraer los tokens de la condición
        ArrayList<String> condExprTokens = new ArrayList<>(clauseTokens.subList(0, clauseTokens.size() - 1));

        // Si hay paréntesis, extraer solo el contenido
        if (condExprTokens.get(0).equals("(") && condExprTokens.get(condExprTokens.size() - 1).equals(")")) {
            condExprTokens = new ArrayList<>(condExprTokens.subList(1, condExprTokens.size() - 1));
        }

        // Evaluar la condición
        condition = evaluarSubexpresion(condExprTokens, a);

        // Si la condición es verdadera, imprimir y salir
        if (condition) {
            a.print(resToken);
            return;
        }
    }

    // Si ninguna condición se cumple, usar el resultado por defecto si existe
    if (defaultResult != null) {
        a.print(defaultResult);
    }
}

// Función auxiliar: Encuentra el índice del paréntesis de cierre que hace match con el paréntesis en 'start' de la cadena 's'.
private int findMatchingParen(String s, int start) {
    if (s.charAt(start) != '(') return -1;
    int count = 0;
    for (int i = start; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        else if (c == ')') count--;
        if (count == 0) return i;
    }
    return -1;
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