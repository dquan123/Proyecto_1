import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operaciones {
    
    public Operaciones() {
        //Constructor vacío
    }

    //Función print para mostrar los resultados en pantalla
    public void print(String cadena){
        System.out.println(cadena); //Imprime en pantalla la cadena que recibió (el parámetro)
    }

    //Función que verifica si el argumento es un átomo (cualquier objeto que no sea una lista)
    public boolean atom(Object obj) {
        return !(obj instanceof ArrayList); //Devuelve true si el objeto no es una lista, false en caso contrario
    }

    // Función que verifica si el argumento es una lista
    public boolean list(Object obj){
        return obj instanceof ArrayList; //Devuelve true si el objeto es una lista, false si no es el caso.
    }

    //Función que hace la suma de la cantidad de valores que vengan en la lista
    public int suma(ArrayList<Integer> argumentos){
        int suma = 0; //Variable para almacenar la suma
        for (int numero : argumentos){ //Recorre la lista de argumentos
            suma += numero; //Suma cada número a la variable suma
        }
        return suma; //Devuelve la suma
    }

    //Función que hace la resta de la cantidad de valores que vengan en la lista
    public int resta(ArrayList<Integer> argumentos){
        int resta = argumentos.get(0); //Variable que comienza con el primer valor de la lista
        for (int numero=1 ; numero < argumentos.size() ; numero++){ //Se recorre un ciclo desde la segunda posición
            resta -= numero; //Se le resta cada número
        }
        return resta; //Devuelve la resta
    }

    //Función que hace la multiplicación de la cantidad de valores que vengan en la lista
    public int multiplicacion(ArrayList<Integer> argumentos){
        int r = 1; //Variable para almacenar la multiplicación
        for (int numero : argumentos){ //Recorre la lista de argumentos
            r *= numero; //Multiplica cada número a la variable r
        }
        return r; //Devuelve el resultado
    }

    //Función que hace la resta de la cantidad de valores que vengan en la lista
    public int division(ArrayList<Integer> argumentos){
        int r = argumentos.get(0); //Variable que comienza con el primer valor de la lista
        for (int numero=1 ; numero < argumentos.size() ; numero++){ //Se recorre un ciclo desde la segunda posición
            r = r / numero; //Se divide entre cada término de la lista
        }
        return r; //Devuelve el resultado
    }

    public boolean menorQue (int a, int b){
        return a < b;
    }

    public boolean mayorQue (int a, int b){
        return a > b;
    }

    public boolean equal(int a , int b){
        return a == b;
    }

    public List<Integer> Quote(List<Integer> argumentos){ //Recibe una lista.
        return argumentos; //Retorna esa misma lista sin hacer ningún cambio.
    }





    public void setq(String variable, String valor) { //Recibe la variable y el valor
        if (valor.equalsIgnoreCase("true")) { //Revisa si el valor es un true (boolean)
            Booleanos.put(variable, (Boolean) true); //Si es un true, lo mete al hashmap de booleanos
            return; //Interrumpe el método para no seguir validando
        } else if (valor.equalsIgnoreCase("false")) { //Revisa si el valor es un false (boolean)
            Booleanos.put(variable, (Boolean) false); //Si es un false, lo mete al hashmap de booleanos
            return; //Interrumpe el método para no seguir validando
        }
        try {
            int z = Integer.parseInt(valor); //Intenta convertirlo a un entero
            enteros.put(variable, (Integer) z); //Si lo logra, lo mete al hashmap de enteros.
            return; //Interrumpe el método para no seguir validando
        } catch (NumberFormatException e) { //Si no lo logra, arroja una excepción.
        }
        Cadenas.put(variable, (String) valor); //Si nada de lo anterior funciona, entonces lo mete al hashmap de cadenas
    }
}