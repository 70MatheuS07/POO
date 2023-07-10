package src;
import java.util.Date;

public class Prova extends Avaliacao {
    public static final int PARCIAL = 0;
    public static final int FINAL = 1;
    private int tipoProva;

    public Prova(String disciplina, String nome, double peso, Date data, int tipoProva) {
        super(disciplina, nome, peso, data);
        this.tipoProva = tipoProva;
    }

    public int getTipoProva() {
        return tipoProva;
    }
}
