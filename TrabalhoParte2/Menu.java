import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Leitura leitura = new Leitura();

        DisciplinaMap disciplinas = leitura.LehDisciplinas();
        CursoMap cursos = leitura.LehCursos();
        AlunoMap alunos = leitura.LehAlunos(disciplinas.getDisciplinaMap());
        leitura.LehAvaliacoes();
        ProvaMap provas = leitura.LehProvas();

    }

    /**
     * Imprime o menu.
     */
    public static void ImprimeMenu() {
        System.out.println("Opcoes:");
        System.out.println("1 - Cadastrar curso.");
        System.out.println("2 - Cadastrar disciplina.");
        System.out.println("3 - Cadastrar prova.");
        System.out.println("4 - Cadastrar aluno(a).");
        System.out.println("5 - Matricular aluno(a) em disciplina.");
        System.out.println("6 - Registrar nota de aluno em prova.");
        System.out.println("7 - Imprimir dados.");
        System.out.println("0 - Sair do programa.");
    }

    /**
     * Imprime os dados.
     * 
     * @param disciplinas
     * @param provas
     * @param cursos
     * @param alunos
     */
    public static void ImprimeDados(DisciplinaMap disciplinas, ProvaMap provas, CursoMap cursos, AlunoMap alunos) {
        disciplinas.DisciplinasAlunosMatriculados(cursos);
        provas.ProvasNotaRecebida(alunos);
    }
}
