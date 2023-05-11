import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        AlunoMap alunos = new AlunoMap();
        CursoMap cursos = new CursoMap();
        DisciplinaMap disciplinas = new DisciplinaMap();
        ProvaMap provas = new ProvaMap();

        int numero = 0;
        boolean valido = false;

        while (true) {
            valido = false;
            ImprimeMenu();

            while (!valido) {
                try {
                    System.out.print("Digite um número inteiro: ");
                    numero = Leitura.LehInt(scanner);
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Você digitou um valor inválido. Tente novamente.");
                    scanner.nextLine();
                }
            }

            switch (numero) {
                case 0:
                    break;

                case 1:
                    cursos.CadastrarCurso(scanner);
                    break;

                case 2:
                    disciplinas.CadastrarDisciplina(scanner);
                    break;

                case 3:
                    provas.CadastrarProva(disciplinas, scanner);
                    break;

                case 4:
                    alunos.CadastrarAluno(scanner);
                    break;

                case 5:
                    disciplinas.MatricularAlunoDisciplina(alunos, scanner);
                    break;

                case 6:
                    alunos.RegistraNotaAlunoProva(scanner);
                    break;

                case 7:
                    ImprimeDados(disciplinas, provas, cursos, alunos);
                    break;

                default:
                    System.out.println("Erro: o número deve estar entre 0 e 7.");
                    break;
            }

            if (numero == 0) {
                break;
            }

        }

        scanner.close();
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
