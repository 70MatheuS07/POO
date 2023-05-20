import java.util.Date;

public class Prova extends Avaliacao {
    // False - Parcial
    // True - Final
    boolean TipoProva;

    public Prova(String disciplina, String nome, double peso, Date data, boolean tipoProva) {
        super(disciplina, nome, peso, data);
        TipoProva = tipoProva;
    }
}
