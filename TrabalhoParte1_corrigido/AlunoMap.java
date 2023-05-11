import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    public void addAlunoMap(int matricula, Aluno aluno) {
        alunos.put(matricula, aluno);
    }

    public void CadastrarAluno(Scanner scanner) {
        int matricula;
        Aluno aluno = new Aluno();

        while (true) {
            System.out.print("Digite sua matricula: ");
            matricula = scanner.nextInt();
            scanner.nextLine();

            if (!alunos.containsKey(matricula)) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        aluno.setNome(Leitura.LehLine(scanner));

        System.out.print("Digite qual curso deseja fazer: ");
        aluno.setCurso(Leitura.LehInt(scanner));
        scanner.nextLine();

        alunos.put(matricula, aluno);
    }

    public void RegistraNotaAlunoProva(Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        Aluno aluno = alunos.get(Leitura.LehInt(scanner));

        System.out.print("Digite o codigo da prova: ");
        String prova = Leitura.LehLine(scanner);

        System.out.print("Digite a nota da prova: ");
        aluno.getNotasProvas().put(prova, Leitura.LehDouble(scanner));
    }

}
