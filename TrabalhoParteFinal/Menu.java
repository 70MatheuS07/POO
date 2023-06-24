import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static String dados = "dados.dat";

    public static void main(String[] args) throws Exception {
        int i = 0;
        String nomeArquivo = null;

        Dados dados = null;
        AlunoMap alunos = null;
        CursoMap cursos = null;
        DisciplinaMap disciplinas = null;
        AvaliacaoMap avaliacoes = null;

        boolean escreverDados = false;
        boolean pulaSwitch = false;

        for (String p : args) {

            // Incompleta ainda.
            if (p.equals("--write-only")) {
                // O arquivo existe, então realizamos a desserialização
                dados = Empacotamento.LerArquivoBinario(Menu.dados);
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
                pulaSwitch = true;
            }

            else if (p.equals("--read-only")) {
                escreverDados = true;
                pulaSwitch = false;
            }
        }

        if (!pulaSwitch) {
            dados = new Dados();
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
                    alunos.CadastrarAlunos(disciplinas, nomeArquivo);
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

        if (escreverDados) {
            dados.setCursos(cursos);
            dados.setDisciplinas(disciplinas);
            dados.setAlunos(alunos);
            dados.setAvaliacoes(avaliacoes);

            Empacotamento.GravarArquivoBinario(Menu.dados, dados);
        }

        else {
            disciplinas.CriaPautaDisciplinas(alunos, avaliacoes);
            disciplinas.CriaDisciplinasCSV(alunos, avaliacoes);
        }

    }
}
