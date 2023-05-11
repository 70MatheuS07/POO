import java.util.Date;

public class Prova {
    private Disciplina disciplina;
    private String nome;
    private double peso;
    private Date data;

    public void setProva(String nome, double peso, Date data) {
        this.nome = nome;
        this.peso = peso;
        this.data = data;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
