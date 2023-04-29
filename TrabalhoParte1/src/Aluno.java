import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Aluno {
    private static Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
    private String nome;
    private int curso;

    public static Map<Integer, Aluno> getAlunos() {
        return alunos;
    }

    public static void setAlunos(Map<Integer, Aluno> alunos) {
        Aluno.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void CadastrarAluno(Scanner scanner) {
        int matricula;
        Aluno aluno = new Aluno();

        while (true) {
            System.out.print("Digite sua matricula: ");
            matricula = scanner.nextInt();

            if (!alunos.containsKey(matricula)) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        aluno.nome = scanner.next();

        System.out.print("Digite qual curso deseja fazer: ");
        aluno.curso = scanner.nextInt();

        alunos.put(matricula, aluno);
    }

}
