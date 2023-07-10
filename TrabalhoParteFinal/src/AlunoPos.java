package src;

public class AlunoPos extends Aluno {
    public static final int MESTRADO = 0;
    public static final int DOUTORADO = 1;
    private int nivel;

    public AlunoPos(String nome, String grad, int nivel) {
        super(nome, grad);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
