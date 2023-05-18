import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Dados dados = null;
        AlunoMap alunos = null;
        CursoMap cursos = null;
        DisciplinaMap disciplinas = null;
        AvaliacaoMap avaliacoes = null;

        File arquivo = new File("dados.ser");

        if (arquivo.exists()) {
            // O arquivo existe, então realizamos a desserialização
            dados = Empacotamento.LerArquivoBinario();
            if (dados != null) {
                // A desserialização foi bem-sucedida, utilize os objetos desserializados
                // conforme necessário
                alunos = dados.getAlunos();
                cursos = dados.getCursos();
                disciplinas = dados.getDisciplinas();
                avaliacoes = dados.getAvaliacoes();
            } else {
                System.out.println("Não foi possível realizar a desserialização do arquivo.");
            }
        } else {
            // O arquivo não existe, crie as variáveis e salve o arquivo
            dados = new Dados();
            alunos = new AlunoMap();
            cursos = new CursoMap();
            disciplinas = new DisciplinaMap();
            avaliacoes = new AvaliacaoMap();
        }

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
                    avaliacoes.CadastrarAvaliacao(disciplinas, scanner);
                    break;

                case 4:
                    alunos.CadastrarAluno(scanner);
                    break;

                case 5:
                    disciplinas.MatricularAlunoDisciplina(alunos, scanner);
                    break;

                case 6:
                    alunos.RegistraNotaAlunoAvaliacao(scanner);
                    break;

                case 7:
                    ImprimeDados(disciplinas, avaliacoes, cursos, alunos);
                    break;

                default:
                    System.out.println("Erro: o número deve estar entre 0 e 7.");
                    break;
            }

            if (numero == 0) {
                if (alunos != null)
                    dados.setAlunos(alunos);
                if (cursos != null)
                    dados.setCursos(cursos);
                if (disciplinas != null)
                    dados.setDisciplinas(disciplinas);
                if (avaliacoes != null)
                    dados.setAvaliacoes(avaliacoes);
                Empacotamento.GravarArquivoBinario(dados);
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
        System.out.println("3 - Cadastrar avaliacao.");
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
     * @param avaliacoes
     * @param cursos
     * @param alunos
     */
    public static void ImprimeDados(DisciplinaMap disciplinas, AvaliacaoMap avaliacoes, CursoMap cursos, AlunoMap alunos) {
        disciplinas.DisciplinasAlunosMatriculados(cursos);
        avaliacoes.avaliacoesNotaRecebida(alunos);
    }
}
