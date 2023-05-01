import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Aluno {
    private static Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();
    private String nome;
    private int curso;
    private Map<String, Double> notasProvas = new HashMap<String, Double>();

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

    public static void ProvasNotaRecebida() {
        System.out.println("Provas e notas recebidas:");

        for (int i = 0; i < alunos.size(); i++) {
            for (String key : alunos.get(i).notasProvas.keySet()) {
                System.out.println("- " + Prova.getProvas().get(key).getDisciplina()
                        + " - " + Prova.getProvas().get(key).getNome() + " + "
                        + "(" + Prova.getProvas().get(key).getData() + ")");

                System.out.print("\t- " + alunos.get(i).nome + ": ");
                System.out.printf("%.2f\n", alunos.get(i).notasProvas.get(key).doubleValue());
            }

        }
    }

}
