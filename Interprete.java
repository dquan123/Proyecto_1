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
    // Si la cadena contiene "cond", usamos el proceso especial; sino, la evaluación normal
    if (cadena.contains("cond")) {
        evaluarCond(tokens, new Operaciones());
    } else {
        evaluarNormal(tokens, new Operaciones());
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


private void evaluarCond(ArrayList<String> tokens, Operaciones a) {
    // Buscar el bloque "cond" en los tokens.
    int indexCond = tokens.indexOf("cond");
    if (indexCond == -1) {
        evaluarNormal(tokens, a);
        return;
    }
    // Retroceder hasta encontrar el "(" que abre la expresión cond.
    int start = indexCond - 1;
    while (start >= 0 && !tokens.get(start).equals("(")) {
        start--;
    }
    if (start < 0)
        throw new RuntimeException("No se encontró el inicio de la expresión cond.");
    
    // Extraer los tokens del bloque cond usando un contador de paréntesis.
    int count = 0;
    ArrayList<String> condTokens = new ArrayList<>();
    for (int i = start; i < tokens.size(); i++) {
        String t = tokens.get(i);
        if (t.equals("(")) count++;
        if (t.equals(")")) count--;
        condTokens.add(t);
        if (count == 0)
            break;
    }
    
    // condTokens debería tener la forma: ["(", "cond", ...cláusulas..., ")"]
    if (!condTokens.isEmpty() && condTokens.get(0).equals("("))
        condTokens.remove(0);
    if (!condTokens.isEmpty() && condTokens.get(condTokens.size()-1).equals(")"))
        condTokens.remove(condTokens.size()-1);
    if (!condTokens.isEmpty() && condTokens.get(0).equals("cond"))
        condTokens.remove(0);
    
    // Ahora, extraer cláusulas.
    // Se espera que cada cláusula esté escrita como: ((<cond-exp>) <resultado>)
    // Por ejemplo: (((> 5 3)) "Mayor")
    ArrayList<Object[]> clausesList = new ArrayList<>();
    int i = 0;
    String defaultResult = null;
    while (i < condTokens.size()) {
        if (condTokens.get(i).equals("(")) {
            // Extraer la condición (que puede estar entre paréntesis) y luego el resultado.
            int clauseCount = 1;
            i++; // Saltar el "(" que abre la cláusula.
            ArrayList<String> conditionTokens = new ArrayList<>();
            // Si la condición está entre paréntesis, extraerla.
            if (i < condTokens.size() && condTokens.get(i).equals("(")) {
                clauseCount = 1;
                i++; // Saltar el "(" que abre la condición.
                while (i < condTokens.size() && clauseCount > 0) {
                    String tk = condTokens.get(i);
                    if (tk.equals("(")) clauseCount++;
                    else if (tk.equals(")")) clauseCount--;
                    if (clauseCount > 0) {
                        conditionTokens.add(tk);
                    }
                    i++;
                }
            } else if (i < condTokens.size()) {
                // Si no hay paréntesis, la condición es el token actual.
                conditionTokens.add(condTokens.get(i));
                i++;
            }
            // Ahora, el siguiente token se asume que es el resultado de la cláusula.
            String resToken = "";
            if (i < condTokens.size()) {
                resToken = condTokens.get(i).trim();
                i++;
            }
            // Opcional: saltar cualquier token de cierre extra (si lo hubiera)
            while (i < condTokens.size() && condTokens.get(i).equals(")")) {
                i++;
            }
            // Si la condición es literal "else" o "t", la tomamos como default.
            if (!conditionTokens.isEmpty() && (conditionTokens.get(0).equalsIgnoreCase("else") || conditionTokens.get(0).equalsIgnoreCase("t"))) {
                defaultResult = resToken;
            } else {
                // Evaluar la condición usando evaluarSubexpresion para obtener "true" o "false".
                String condEval = evaluarSubexpresion(conditionTokens, a);
                boolean condVal = condEval.trim().equalsIgnoreCase("true") || condEval.trim().equalsIgnoreCase("t");
                clausesList.add(new Object[]{condVal, resToken});
            }
        } else {
            i++;
        }
    }
    
    // Convertir la lista a una matriz.
    Object[][] clausesArray = new Object[clausesList.size()][2];
    for (int j = 0; j < clausesList.size(); j++) {
        clausesArray[j] = clausesList.get(j);
    }
    
    String condResult;
    if (clausesArray.length == 0) {
        condResult = defaultResult;
    } else {
        condResult = a.cond(clausesArray, defaultResult);
    }
    if (cadena.contains("print")) {
        a.print(condResult);
    }
}

// Método auxiliar para evaluar una subexpresión (condición) y devolver el resultado como String.
// Esta versión es muy simplificada y solo maneja comparaciones básicas: >, <, equal.
private String evaluarSubexpresion(ArrayList<String> tokens, Operaciones a) {
    // Unir los tokens en una cadena.
    StringBuilder sb = new StringBuilder();
    for (String t : tokens) {
        sb.append(t).append(" ");
    }
    String expr = sb.toString().trim();
    // Por ejemplo, si expr es "> 5 3", evaluarlo:
    if (expr.startsWith(">")) {
        String[] parts = expr.split("\\s+");
        if (parts.length >= 3) {
            int op1 = Integer.parseInt(parts[1]);
            int op2 = Integer.parseInt(parts[2]);
            return (op1 > op2) ? "true" : "false";
        }
    } else if (expr.startsWith("<")) {
        String[] parts = expr.split("\\s+");
        if (parts.length >= 3) {
            int op1 = Integer.parseInt(parts[1]);
            int op2 = Integer.parseInt(parts[2]);
            return (op1 < op2) ? "true" : "false";
        }
    } else if (expr.startsWith("equal")) {
        String[] parts = expr.split("\\s+");
        if (parts.length >= 3) {
            int op1 = Integer.parseInt(parts[1]);
            int op2 = Integer.parseInt(parts[2]);
            return (op1 == op2) ? "true" : "false";
        }
    } else if (expr.equalsIgnoreCase("true") || expr.equalsIgnoreCase("t")) {
        return "true";
    } else if (expr.equalsIgnoreCase("false")) {
        return "false";
    }
    // Por defecto, si no se reconoce, se retorna la cadena tal cual.
    return expr;
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