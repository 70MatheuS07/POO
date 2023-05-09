import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Disciplina extends Aluno {
    private String nome;
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Aluno> getAlunosDisciplina() {
        return alunos;
    }

    public void setAlunosDisciplina(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void CadastrarDisciplina(Scanner scanner) {
        String codigo;

        while (true) {
            System.out.print("Digite o codigo da disciplina: ");
            codigo = scanner.nextLine();

            if (!disciplinas.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");
        }

        System.out.print("Digite o nome da disciplina: ");
        this.nome = scanner.nextLine();

        disciplinas.put(codigo, this);
    }

    public static Disciplina SelecionaDisciplina(Scanner scanner) {
        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = scanner.nextLine();

        if (disciplinas.containsKey(disciplina)) {
            return disciplinas.get(disciplina);
        }

        return null;
    }

    public static void MatricularAlunoDisciplina(Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        Aluno aluno = Aluno.getAlunos().get(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = scanner.nextLine();

        disciplinas.get(disciplina).alunos.add(aluno);
    }

    public static void DisciplinasAlunosMatriculados() {
        System.out.println("Disciplinas e alunos matriculados:");

        for (String key : disciplinas.keySet()) {
            System.out.println("- " + disciplinas.get(key).nome + " (" + key + ")");

            ImprimeAlunosDisciplina(disciplinas.get(key));

        }
    }

    public static void ImprimeAlunosDisciplina(Disciplina disciplina) {
        for (int i = 0; i < disciplina.alunos.size(); i++) {
            System.out.println("\t- " + disciplina.alunos.get(i).getNome() +
                    " (" + Curso.getCursos().get(disciplina.alunos.get(i).getCurso()).getNome() + ")");
        }
    }

}
