import java.util.HashMap;

public class Entorno {
    private HashMap<String, Integer> enteros;
    private HashMap<String, String> Cadenas;
    private HashMap<String, Boolean> Booleanos;
    private HashMap<String, FuncionLisp> Funciones;

    public Entorno(){
        this.enteros = new HashMap<>();
        this.Cadenas = new HashMap<>();
        this.Booleanos = new HashMap<>();
        this.Funciones = new HashMap<>();
    }

    public HashMap<String, String> getCadenas() {
        return this.Cadenas;
    }

    public HashMap<String, Integer> getEnteros() {
        return this.enteros;
    }

    public HashMap<String, Boolean> getBooleanos() {
        return this.Booleanos;
    }

    public HashMap<String, FuncionLisp> getFunciones() {
        return Funciones;
    }

    /**
     * Método buscar: recibe un nombre y un HashMap en el que buscará dicho nombre,
     * retornando el valor asociado.
     * @param nombre La clave a buscar.
     * @param matriz El HashMap en el que se realizará la búsqueda.
     * @return El valor asociado a la clave, o null si no se encuentra.
     */
    public Object buscar(String nombre, HashMap matriz) {
        return matriz.get(nombre);
    }
}
