package src;
import java.io.Serializable;
import java.util.Date;

public class Avaliacao implements Serializable {
    private String disciplinaKey;
    private String nome;
    private double peso;
    private Date data;

    public Avaliacao(String disciplina, String nome, double peso, Date data) {
        this.disciplinaKey = disciplina;
        this.nome = nome;
        this.peso = peso;
        this.data = data;
    }

    public String getDisciplinaKey() {
        return disciplinaKey;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public Date getData(){
        return data;
    }
}
