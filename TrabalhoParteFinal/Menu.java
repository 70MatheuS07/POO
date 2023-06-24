import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        boolean lehDados = false;
        boolean pulaSwitch = false;
        try{
        for (String p : args) {

            //Incompleta ainda.
            if (p.equals("--write-only")) {
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
                lehDados = true;
                pulaSwitch = true;
            }

            else if (p.equals("--read-only")) {
                escreverDados = true;
                pulaSwitch = false;
            }
        }

        if (!pulaSwitch) {
            cursos = new CursoMap();
            disciplinas = new DisciplinaMap();
            alunos = new AlunoMap();
            avaliacoes = new AvaliacaoMap();

            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-c")) {
                    i++;
                    nomeArquivo = args[i];
                    cursos.CadastrarCursos(nomeArquivo);
                    break;
                }
            }

            cursos.ImprimeCursosCSV();

            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-d")) {
                    i++;
                    nomeArquivo = args[i];
                    disciplinas.CadastrarDisciplinas(nomeArquivo);
                    break;
                }
            }

            disciplinas.ImprimeDisciplinaCSV();

            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-a")) {
                    i++;
                    nomeArquivo = args[i];
                    alunos.CadastrarAlunos(cursos,disciplinas, nomeArquivo);
                    break;
                }
            }

            disciplinas.ImprimeDisciplinaCSV();

            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-p")) {
                    i++;
                    nomeArquivo = args[i];
                    avaliacoes.CadastrarAvaliacoes(disciplinas, nomeArquivo);
                    break;
                }
            }

            for (i = 0; i < args.length; i++) {
                if (args[i].equals("-n")) {
                    i++;
                    nomeArquivo = args[i];
                    alunos.RegistraNotaAlunoAvaliacao(avaliacoes, disciplinas, nomeArquivo);
                    break;
                }
            }
        }
    }
    catch(Exception e){
    try (FileWriter writer = new FileWriter("Output.txt");
         PrintWriter printWriter = new PrintWriter(writer)) {
        e.printStackTrace(printWriter);
    } catch (IOException ioException) {
        ioException.printStackTrace();
    }
    }
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
