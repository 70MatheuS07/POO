import java.util.Date;

public class Trabalho extends Avaliacao {
    
    int qtdGrupos;

    public Trabalho(Disciplina disciplina, String nome, double peso, Date data, int qtdGrupos) {
        super(disciplina, nome, peso, data);
        this.qtdGrupos = qtdGrupos;
    }
    public int GetqtdGrupos(){
        return this.qtdGrupos;
    }
}