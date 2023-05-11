import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    public void setAlunoMap(Map<Integer, Aluno> alunos)
    {
        this.alunos = alunos;
    }

    public void CadastrarAluno(Scanner scanner) {
        int matricula;

        while (true) {
            System.out.print("Digite sua matricula: ");
            matricula = Leitura.LehInt(scanner);

            if (!alunos.containsKey(matricula)) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        String nome = Leitura.LehLine(scanner);

        System.out.print("Digite qual curso deseja fazer: ");
        int curso = Leitura.LehInt(scanner);

        Aluno aluno = new Aluno();

        aluno.setAluno(nome, curso);

        alunos.put(matricula, aluno);
    }

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
