package src.curso;
import java.io.Serializable;

public class Curso implements Serializable {
    private String nome;

    public void setCurso(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
