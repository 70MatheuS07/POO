import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisciplinaMap {
    private Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();

    public Map<String, Disciplina> getDisciplinaMap() {
        return disciplinas;
    }

    public void CadastrarDisciplina(Scanner scanner) {
        String codigo;

        Disciplina disciplina = new Disciplina();

        while (true) {
            System.out.print("Digite o codigo da disciplina: ");
            codigo = Leitura.LehLine(scanner);

            if (!disciplinas.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");
        }

        System.out.print("Digite o nome da disciplina: ");
        disciplina.setDisciplina(Leitura.LehLine(scanner));

        disciplinas.put(codigo, disciplina);
    }

    public Disciplina SelecionaDisciplina(Scanner scanner) {
        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = Leitura.LehLine(scanner);

        if (disciplinas.containsKey(disciplina)) {
            return disciplinas.get(disciplina);
        }

        return null;
    }

    public void MatricularAlunoDisciplina(AlunoMap alunos, Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        int matricula = Leitura.LehInt(scanner);
        Aluno aluno = alunos.getAlunoMap().get(matricula);

        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = Leitura.LehLine(scanner);

        disciplinas.get(disciplina).getAlunoMap().getAlunoMap().put(matricula, aluno);
    }

    public void DisciplinasAlunosMatriculados(CursoMap cursos) {
        System.out.println("Disciplinas e alunos matriculados:");

        for (String key : disciplinas.keySet()) {
            System.out.println("- " + disciplinas.get(key).getNome() + " (" + key + ")");

            disciplinas.get(key).ImprimeAlunosDisciplina(cursos);

        }
    }
}
