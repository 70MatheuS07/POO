import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int numero=0;
        boolean valido=false;
        while (true) {
            ImprimeMenu();

            while (!valido) {
                try {
                    System.out.print("Digite um número inteiro: ");
                    numero = scanner.nextInt();
                    valido = true;
                } catch (InputMismatchException e) {
                    System.out.println("Você digitou um valor inválido. Tente novamente.");
                    scanner.next(); 
                }
            }

            switch (numero) {
                case 0:
                    break;

                case 1:
                    Curso curso = new Curso();
                    curso.CadastrarCurso(scanner);
                    Curso.cursos.add(curso);
                    scanner.nextLine();
                    break;

                case 2:
                    Disciplina disciplina = new Disciplina();
                    disciplina.CadastrarDisciplina(scanner);
                    Disciplina.disciplinas.add(disciplina);
                    scanner.nextLine();
                    break;

                case 3:
                    Prova prova = new Prova();
                    prova.CadastrarProva(scanner);
                    Prova.provas.add(prova);
                    scanner.nextLine();
                    break;

                case 4:
                    // CadastrarAluno();
                    break;

                case 5:
                    // MatricularAlunoDisciplina();
                    break;

                case 6:
                    // RegistrarNotaAlunoDisciplina();
                    break;

                case 7:
                    // ImprimirDados();
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
