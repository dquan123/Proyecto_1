import java.util.HashMap;

public class Entorno {
    private HashMap<String, Integer> enteros;
    private HashMap<String, String> Cadenas;
    private HashMap<String, Boolean> Booleanos;

    public Entorno(){
        this.enteros = new HashMap<>();
        this.Cadenas = new HashMap<>();
        this.Booleanos = new HashMap<>();
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
}
