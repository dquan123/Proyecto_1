import java.util.ArrayList;

public class Operaciones {
    
    public Operaciones() {
        //Constructor vacío
    }

    //Función print para mostrar los resultados en pantalla
    public void print(String cadena){
        System.out.println(cadena); //Imprime en pantalla la cadena que recibió (el parámetro)
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
}