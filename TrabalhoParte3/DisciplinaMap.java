import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisciplinaMap implements Serializable {
    private Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();

    public Map<String, Disciplina> getDisciplinaMap() {
        return disciplinas;
    }

    /**
     * Cadastra disciplina passada pelo terminal.
     * 
     * @param scanner
     */
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

        String nome = Leitura.LehLine(scanner);

        disciplina.setDisciplina(nome);

        disciplinas.put(codigo, disciplina);
    }

    /**
     * Matricula aluno na disciplina. O aluno é selecionado pela sua matricula
     * e a disciplina pelo seu código. Tudo via terminal.
     * 
     * @param alunos
     * @param scanner
     */
    public void MatricularAlunoDisciplina(AlunoMap alunos, Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        int matricula = Leitura.LehInt(scanner);
        Aluno aluno = alunos.getAlunoMap().get(matricula);

        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = Leitura.LehLine(scanner);

        disciplinas.get(disciplina).getAlunoMap().getAlunoMap().put(matricula, aluno);
    }

    /**
     * Imprime as disciplinas com seus alunos matriculados.
     * 
     * @param cursos
     */
    public void DisciplinasAlunosMatriculados(CursoMap cursos) {
        System.out.println("Disciplinas e alunos matriculados:");

        for (Map.Entry<String, Disciplina> entry : disciplinas.entrySet()) {
            String codigo = entry.getKey();
            Disciplina disciplina = entry.getValue();
            String nome = disciplina.getNome();

            System.out.printf("- %s (%s)%n", nome, codigo);

            disciplina.ImprimeAlunosDisciplina(cursos);
            System.out.println(); // adiciona uma linha em branco entre as disciplinas
        }
    }
}
