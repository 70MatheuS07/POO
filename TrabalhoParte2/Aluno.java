import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Aluno implements Serializable {
    protected String nome;
    protected String Grad;
    protected Map<String, Double> notasProvas = new HashMap<String, Double>();

    public Aluno(String nome, String Grad) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Double> getNotasProvas() {
        return notasProvas;
    }

    public void setNotasProvas(Map<String, Double> notasProvas) {
        this.notasProvas = notasProvas;
    }

}
