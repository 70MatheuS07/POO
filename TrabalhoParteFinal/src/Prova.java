package src;
import java.util.Date;

public class Prova extends Avaliacao {
    // False - Parcial
    // True - Final
    boolean tipoProva;

    public Prova(String disciplina, String nome, double peso, Date data, boolean tipoProva) {
        super(disciplina, nome, peso, data);
        this.tipoProva = tipoProva;
    }

    public boolean getTipoProva() {
        return tipoProva;
    }
}
