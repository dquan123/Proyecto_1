import java.util.HashMap;

public class Entorno {
    private HashMap<String, Integer> enteros;
    private HashMap<String, String> Cadenas;
    private HashMap<String, Boolean> Booleanos;

    public Entorno(){
        HashMap<String, Integer> enteros = new HashMap<>();
        HashMap<String, String> Cadenas = new HashMap<>();
        HashMap<String, Boolean> Booleanos = new HashMap<>();
    }

    public HashMap<String, String> getCadenas() {
        return Cadenas;
    }

    public HashMap<String, Integer> getEnteros() {
        return enteros;
    }

    public HashMap<String, Boolean> getBooleanos() {
        return Booleanos;
    }
}
