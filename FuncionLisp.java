import java.util.List;

public class FuncionLisp {
    private List<String> parametros;
    private List<String> cuerpo;

    public FuncionLisp(List<String> parametros, List<String> cuerpo) {
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public List<String> getCuerpo() {
        return cuerpo;
    }
}
