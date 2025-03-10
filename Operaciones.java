import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operaciones {
    
    /**
     * Constructor vacío de la clase Operaciones.
     */
    public Operaciones() {
        // Constructor vacío
    }

    /**
     * Función print para mostrar los resultados en pantalla.
     * @param cadena La cadena de texto a imprimir.
     */
    public void print(String cadena){
        System.out.println(cadena); // Imprime en pantalla la cadena que recibió (el parámetro)
    }

    /**
     * Función que verifica si el argumento es un átomo (cualquier objeto que no sea una lista).
     * @param obj El objeto a evaluar.
     * @return true si el objeto no es una lista, false en caso contrario.
     */
    public boolean atom(Object obj) {
        return !(obj instanceof ArrayList); // Devuelve true si el objeto no es una lista, false en caso contrario
    }

    /**
     * Función que verifica si el argumento es una lista.
     * @param obj El objeto a evaluar.
     * @return true si el objeto es una lista, false si no lo es.
     */
    public boolean list(Object obj){
        return obj instanceof ArrayList; // Devuelve true si el objeto es una lista, false en caso contrario
    }

    /**
     * Función que realiza la suma de los valores en la lista.
     * @param argumentos Lista de enteros a sumar.
     * @return La suma de los valores en la lista.
     */
    public int suma(ArrayList<Integer> argumentos){
        int suma = 0; // Variable para almacenar la suma
        for (int numero : argumentos){ // Recorre la lista de argumentos
            suma += numero; // Suma cada número a la variable suma
        }
        return suma; // Devuelve la suma
    }

    /**
     * Función que realiza la resta de los valores en la lista.
     * @param argumentos Lista de enteros a restar.
     * @return El resultado de la resta.
     */
    public int resta(ArrayList<Integer> argumentos){
        int resta = argumentos.get(0); // Variable que comienza con el primer valor de la lista
        for (int numero=1 ; numero < argumentos.size() ; numero++){ // Se recorre un ciclo desde la segunda posición
            resta -= numero; // Se le resta cada número
        }
        return resta; // Devuelve la resta
    }

    /**
     * Función que realiza la multiplicación de los valores en la lista.
     * @param argumentos Lista de enteros a multiplicar.
     * @return El resultado de la multiplicación.
     */
    public int multiplicacion(ArrayList<Integer> argumentos){
        int r = 1; // Variable para almacenar la multiplicación
        for (int numero : argumentos){ // Recorre la lista de argumentos
            r *= numero; // Multiplica cada número a la variable r
        }
        return r; // Devuelve el resultado
    }

    /**
     * Función que realiza la división de los valores en la lista.
     * @param argumentos Lista de enteros a dividir.
     * @return El resultado de la división.
     */
    public int division(ArrayList<Integer> argumentos){
        int r = argumentos.get(0); // Variable que comienza con el primer valor de la lista
        for (int numero=1 ; numero < argumentos.size() ; numero++){ // Se recorre un ciclo desde la segunda posición
            r = r / numero; // Se divide entre cada término de la lista
        }
        return r; // Devuelve el resultado
    }

    /**
     * Función que verifica si el primer número es menor que el segundo.
     * @param a Primer número.
     * @param b Segundo número.
     * @return true si a es menor que b, false en caso contrario.
     */
    public boolean menorQue (int a, int b){
        return a < b;
    }

    /**
     * Función que verifica si el primer número es mayor que el segundo.
     * @param a Primer número.
     * @param b Segundo número.
     * @return true si a es mayor que b, false en caso contrario.
     */
    public boolean mayorQue (int a, int b){
        return a > b;
    }

    /**
     * Función que verifica si dos números son iguales.
     * @param a Primer número.
     * @param b Segundo número.
     * @return true si los números son iguales, false en caso contrario.
     */
    public boolean equal(int a , int b){
        return a == b;
    }

    /**
     * Función Quote que devuelve la lista de entrada sin modificaciones.
     * @param argumentos Lista de enteros.
     * @return La misma lista recibida como entrada.
     */
    public List<Integer> Quote(List<Integer> argumentos){
        return argumentos; // Retorna esa misma lista sin hacer ningún cambio
    }

    // HashMaps para almacenar diferentes tipos de variables
    private HashMap<String, Integer> enteros = new HashMap<>();
    private HashMap<String, String> Cadenas = new HashMap<>();
    private HashMap<String, Boolean> Booleanos = new HashMap<>();

    /**
     * Función que asigna un valor a una variable en el entorno de ejecución.
     * La variable puede ser de tipo entero, booleano o cadena.
     * @param variable Nombre de la variable.
     * @param valor Valor a asignar.
     */
    public void setq(String variable, String valor) {
        if (valor.equalsIgnoreCase("true")) { // Revisa si el valor es un true (boolean)
            Booleanos.put(variable, true); // Si es un true, lo mete al hashmap de booleanos
            return; // Interrumpe el método para no seguir validando
        } else if (valor.equalsIgnoreCase("false")) { // Revisa si el valor es un false (boolean)
            Booleanos.put(variable, false); // Si es un false, lo mete al hashmap de booleanos
            return; // Interrumpe el método para no seguir validando
        }
        try {
            int z = Integer.parseInt(valor); // Intenta convertirlo a un entero
            enteros.put(variable, z); // Si lo logra, lo mete al hashmap de enteros.
            return; // Interrumpe el método para no seguir validando
        } catch (NumberFormatException e) { // Si no lo logra, arroja una excepción.
        }
        Cadenas.put(variable, valor); // Si nada de lo anterior funciona, entonces lo mete al hashmap de cadenas
    }
}
