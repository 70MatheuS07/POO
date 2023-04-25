import java.util.ArrayList;
import java.util.Scanner;

public class Disciplina {
    private static ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private String codigo;
    private String nome;
    private ArrayList<Aluno> alunosDisciplina = new ArrayList<Aluno>();

    public static ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public static void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        Disciplina.disciplinas = disciplinas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Aluno> getAlunosDisciplina() {
        return alunosDisciplina;
    }

    public void setAlunosDisciplina(ArrayList<Aluno> alunosDisciplina) {
        this.alunosDisciplina = alunosDisciplina;
    }

    public void CadastrarDisciplina(Scanner scanner) {
        while (true) {
            System.out.print("Digite o codigo da disciplina: ");
            this.codigo = scanner.next();

            boolean iguais = false;

            for (int i = 0; i < disciplinas.size(); i++) {
                if (disciplinas.get(i).codigo.equals(this.codigo)) {
                    iguais = true;
                    break;
                }
            }

            if (!iguais) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");

        }

        System.out.print("Digite o nome da disciplina: ");
        this.nome = scanner.next();
    }

    public static String SelecionaDisciplina(Scanner scanner) {
        if (disciplinas.size() < 1) {

            return null;
        }

        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            System.out.println((i + 1) + " " + disciplina.nome + " - " + disciplina.codigo);
        }

        int num = 0;

        while (true) {
            System.out.print("Digite o numero da disciplina para a prova: ");
            num = scanner.nextInt();

            if (num > 0 || num <= disciplinas.size()) {
                break;
            } else {
                System.out.println("Digite um numero valido.");
                System.out.println();
            }
        }

        return disciplinas.get(num - 1).codigo;

    }

    public static void MatricularAlunoDisciplina(Scanner scanner) {
        int matricula = Aluno.SelecionaAluno(scanner);

        if (matricula == -1) {
            System.out.println("Nao existe alunos para serem escolhidos");
        }

        else {
            if (disciplinas.size() < 1) {

                System.out.println("Nao ha disciplinas para matricular o aluno.");
            }

            else {
                for (int i = 0; i < disciplinas.size(); i++) {
                    Disciplina disciplina = disciplinas.get(i);
                    System.out.println((i + 1) + ". " + disciplina.nome + " - " + disciplina.codigo);
                }

                int num = 0;

                while (true) {
                    System.out.print("Qual disciplina deseja selecionar: ");
                    num = scanner.nextInt();

                    if (num > 0 || num <= disciplinas.size()) {
                        break;
                    } else {
                        System.out.println("Numero invalido. Digite um inteiro exibido.\n");
                    }
                }

                disciplinas.get(num).alunosDisciplina.add(Aluno.getAlunos().get(matricula));
            }
        }
    }
}
