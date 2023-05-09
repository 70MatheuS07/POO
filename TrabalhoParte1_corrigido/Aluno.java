import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Aluno extends Prova{
    private String nome;
    private int curso;
    private Map<String, Double> notasProvas = new HashMap<String, Double>();

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

    public Map<String, Double> getNotasProvas() {
        return notasProvas;
    }

    public void setNotasProvas(Map<String, Double> notasProvas) {
        this.notasProvas = notasProvas;
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
        aluno.nome = scanner.nextLine();

        System.out.print("Digite qual curso deseja fazer: ");
        aluno.curso = scanner.nextInt();
        scanner.nextLine();

        alunos.put(matricula, aluno);
    }

}
