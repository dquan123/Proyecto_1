import java.util.ArrayList;
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
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("print")){
                if (tokens.get(i-1).equals("(")) {
                    a.print(tokens.get(i+1));
                } else {
                    System.out.println("Hay un error de sintaxis en el print");
                }
            }
        }
    }
    
}