import java.util.HashMap;
import java.util.Map;

public class Aluno {
    private String nome;
    private String tipo;
    private Object curso;
    private Map<String, Double> notasProvas = new HashMap<String, Double>();

    public void setAluno(String nome, String tipo, Object curso) {
        this.nome = nome;
        this.tipo = tipo;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getCurso() {
        return curso;
    }

    public void setCurso(Object curso) {
        this.curso = curso;
    }

    public Map<String, Double> getNotasProvas() {
        return notasProvas;
    }

    public void setNotasProvas(Map<String, Double> notasProvas) {
        this.notasProvas = notasProvas;
    }

}
