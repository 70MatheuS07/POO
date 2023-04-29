import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Disciplina {
    private static Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();
    private String nome;
    private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    public static Map<String, Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public static void setDisciplinas(Map<String, Disciplina> disciplinas) {
        Disciplina.disciplinas = disciplinas;
    }

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
            codigo = scanner.next();

            if (!disciplinas.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");
        }

        System.out.print("Digite o nome da disciplina: ");
        this.nome = scanner.next();

        disciplinas.put(codigo, this);
    }

    public static Disciplina SelecionaDisciplina(Scanner scanner) {
        String disciplina = scanner.next();

        if (disciplinas.containsKey(disciplina)) {
            return disciplinas.get(disciplina);
        }

        return null;
    }

    public static void MatricularAlunoDisciplina(Scanner scanner) {
        Aluno aluno = Aluno.getAlunos().get(scanner.nextInt());
        String disciplina = scanner.next();
        disciplinas.get(disciplina).alunos.add(aluno);
    }
}
