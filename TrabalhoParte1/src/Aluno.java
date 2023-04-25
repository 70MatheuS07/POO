import java.util.ArrayList;
import java.util.Scanner;

public class Aluno {
    private static ArrayList<Aluno> alunos = new ArrayList<Aluno>();
    private int matricula;
    private String nome;
    private int curso;

    public static ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public static void setAlunos(ArrayList<Aluno> alunos) {
        Aluno.alunos = alunos;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
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
        while (true) {
            System.out.print("Digite sua matricula: ");
            this.matricula = scanner.nextInt();

            boolean iguais = false;

            for (int i = 0; i < alunos.size(); i++) {
                if (alunos.get(i).matricula == this.matricula) {
                    iguais = true;
                    break;
                }
            }

            if (!iguais) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        this.nome = scanner.next();

        System.out.print("Digite qual curso deseja fazer: ");
        this.curso = scanner.nextInt();
    }

    public static int SelecionaAluno(Scanner scanner) {
        if (alunos.size() < 1) {

            return -1;
        }

        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            System.out.println((i + 1) + ". " + aluno.nome + " - " + aluno.matricula);
        }

        int num = 0;

        while (true) {
            System.out.print("Qual aluno deseja matricular: ");
            num = scanner.nextInt();

            if (num > 0 || num <= alunos.size()) {
                break;
            } else {
                System.out.println("Numero invalido. Digite um inteiro exibido.\n");
            }
        }

        return num - 1;
    }

}
