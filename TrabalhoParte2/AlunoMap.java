import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    public void setAlunoMap(Map<Integer, Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * Cadastra um aluno passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarAluno(int matricula, Aluno aluno) {
        alunos.put(matricula, aluno);
    }

    /**
     * Registra nota do aluno no prova específica.
     * 
     * @param scanner
     */
    public void RegistraNotaAlunoProva(Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        int matricula = Leitura.LehInt(scanner);
        Aluno aluno = alunos.get(matricula);

        System.out.print("Digite o codigo da prova: ");
        String prova = Leitura.LehLine(scanner);

        System.out.print("Digite a nota da prova: ");
        double nota = Leitura.LehDouble(scanner);

        aluno.getNotasProvas().put(prova, nota);
    }

}
