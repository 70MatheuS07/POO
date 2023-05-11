import java.util.HashMap;
import java.util.Map;

public class Aluno {
    private String nome;
    private int curso;
    private Map<String, Double> notasProvas = new HashMap<String, Double>();

    public void setAluno(String nome, int curso) {
        this.nome = nome;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public Map<String, Double> getNotasProvas() {
        return notasProvas;
    }

    public void setNotasProvas(Map<String, Double> notasProvas) {
        this.notasProvas = notasProvas;
    }

}
