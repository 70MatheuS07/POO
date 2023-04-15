import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ImprimeMenu();

            String linha = scanner.nextLine();
            int numero = Integer.parseInt(linha);

            switch (numero) {
                case 0:
                    break;

                case 1:
                    CadastrarCurso();
                    break;

                case 2:
                    CadastrarDisciplina();
                    break;

                case 3:
                    CadastrarProva();
                    break;

                case 4:
                    CadastrarAluno();
                    break;

                case 5:
                    MatricularAlunoDisciplina();
                    break;

                case 6:
                    RegistrarNotaAlunoDisciplina();
                    break;

                case 7:
                    ImprimirDados();
                    break;

                case 7:
                    CadastrarDisciplina();
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
}
