import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        String nomeArquivo = null;

        Dados dados = null;
        AlunoMap alunos = null;
        CursoMap cursos = null;
        DisciplinaMap disciplinas = null;
        AvaliacaoMap avaliacoes = null;

        boolean escreverDados = false;

        while (i < args.length) {

            for (String p : args) {
                if (p.equals("--read-only")) {
                    // O arquivo existe, então realizamos a desserialização
                    dados = Empacotamento.LerArquivoBinario("dados.dat");
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
                }

                if(p.equals("--write-only")){
                    escreverDados = true;
                }
            }

            switch (args[i]) {
                case "-c":
                    i++;
                    nomeArquivo = args[i];
                    cursos.LehPlanilhaCursos(nomeArquivo);
                    i++;
                    break;

                case "-d":
                    i++;
                    nomeArquivo = args[i];
                    disciplinas.LehPlanilhaDisciplinas(nomeArquivo);
                    i++;
                    break;

                case "-p":
                    i++;
                    nomeArquivo = args[i];
                    avaliacoes.LehPlanilhaAvaliacoes(nomeArquivo);
                    i++;
                    break;

                case "-a":
                    i++;
                    nomeArquivo = args[i];
                    alunos.LehPlanilhaAlunos(nomeArquivo);
                    i++;
                    break;

                case "-n":
                    i++;
                    nomeArquivo = args[i];
                    provas.LehPlanilhaProvas(nomeArquivo);
                    i++;
                    break;
            }
        }

        /*
         * int numero = 0;
         * boolean valido = false;
         * Relatorio relatorio = new Relatorio();
         * 
         * while (true) {
         * valido = false;
         * ImprimeMenu();
         * 
         * while (!valido) {
         * try {
         * System.out.print("Digite um número inteiro: ");
         * numero = Leitura.LehInt(scanner);
         * valido = true;
         * } catch (InputMismatchException e) {
         * System.out.println("Você digitou um valor inválido. Tente novamente.");
         * scanner.nextLine();
         * }
         * }
         * 
         * switch (numero) {
         * case 0:
         * break;
         * 
         * case 1:
         * try{
         * cursos.CadastrarCurso(scanner);
         * }
         * catch(Excecao.CodigosIguaisException e){
         * 
         * }
         * break;
         * 
         * case 2:
         * disciplinas.CadastrarDisciplina(scanner);
         * break;
         * 
         * case 3:
         * avaliacoes.CadastrarAvaliacao(disciplinas, scanner);
         * break;
         * 
         * case 4:
         * alunos.CadastrarAluno(scanner);
         * break;
         * 
         * case 5:
         * disciplinas.MatricularAlunoDisciplina(alunos, scanner);
         * break;
         * 
         * case 6:
         * alunos.RegistraNotaAlunoAvaliacao(avaliacoes, disciplinas, scanner);
         * break;
         * 
         * case 7:
         * ImprimeDados(disciplinas, avaliacoes, cursos, alunos);
         * break;
         * 
         * case 8:
         * relatorio.EscolheRelatorio(scanner, disciplinas, avaliacoes);
         * break;
         * 
         * default:
         * System.out.println("Erro: o número deve estar entre 0 e 8.");
         * break;
         * }
         * 
         * if (numero == 0) {
         * 
         * System.out.printf("Quer salvar todos os dados cadastrados?\n");
         * System.out.printf("1 - Sim\n");
         * System.out.printf("2 - Não\n");
         * String resp = Leitura.LehLine(scanner);
         * 
         * if (resp.equals("1")) {
         * dados.setAlunos(alunos);
         * dados.setCursos(cursos);
         * dados.setDisciplinas(disciplinas);
         * dados.setAvaliacoes(avaliacoes);
         * 
         * System.out.printf("Digite o nome do arquivo: ");
         * String nomeArquivo = Leitura.LehLine(scanner);
         * Empacotamento.GravarArquivoBinario(nomeArquivo, dados);
         * }
         * 
         * break;
         * }
         * 
         * }
         * 
         * scanner.close();
         */
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
        System.out.println("8 - Imprimir relatório.");
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
    public static void ImprimeDados(DisciplinaMap disciplinas, AvaliacaoMap avaliacoes, CursoMap cursos,
            AlunoMap alunos) {
        disciplinas.DisciplinasAlunosMatriculados(cursos);
        avaliacoes.avaliacoesNotaRecebida(alunos, disciplinas);
    }
}
