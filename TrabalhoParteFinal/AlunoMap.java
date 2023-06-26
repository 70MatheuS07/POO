import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap implements Serializable {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    /**
     * Cadastra um aluno passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarAlunos(CursoMap cursos, DisciplinaMap disciplinas, String arquivo) throws Excecao {

        File disciplinaFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);
            Aluno aluno = null;

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);
                if (alunos.containsKey(matricula)) {
                    throw new Excecao.MatriculasIguaisException(matricula);
                }
                String nome = dados[1];
                String[] disciplinasCSV = dados[2].split(", ");
                for (String disciplina : disciplinasCSV) {
                    if (!(disciplinas.getDisciplinaMap().containsKey(disciplina))) {
                        throw new Excecao.CodDisciplinaIndefinidoAlunoExcpetion(matricula, disciplina);
                    }
                }
                String tipo = dados[3];

                if (tipo.equals("G")) {
                    int curso = Integer.parseInt(dados[4]);
                    if (!(cursos.getCursoMap().containsKey(curso))) {
                        throw new Excecao.CodCursoIndefinidoException(matricula, curso);
                    }
                    aluno = new AlunoGrad(nome, "G", curso);
                }

                else if (tipo.equals("P")) {
                    String curso = dados[4];
                    if (curso.equals("M"))
                        aluno = new AlunoPos(nome, "P", "Mestrado");

                    else if (curso.equals("D")) {
                        aluno = new AlunoPos(nome, "P", "Doutorado");
                    } else {
                        throw new Excecao.NemMNemDException(matricula, curso);
                    }
                } else {
                    throw new Excecao.NemGNemPException(matricula, tipo);
                }

                // Aparentemente coloca alunos nas disciplinas, porém na hora de imprimir o Map
                // de alunos dentro da disciplina dá erro.
                for (String p : disciplinasCSV) {
                    Disciplina d = disciplinas.getDisciplinaMap().get(p);

                    d.getAlunoMap().getAlunoMap().put(matricula, aluno);
                }

                alunos.put(matricula, aluno);
            }
        }

        catch (IOException e) {

            throw new Excecao("Erro de I/O");

        }

    }

    /**
     * Registra nota do aluno no prova específica.
     * 
     * @param scanner
     */
    public void RegistraNotaAlunoAvaliacao(AvaliacaoMap avaliacoes, DisciplinaMap disciplinas, String arquivo)
            throws Excecao {

        File disciplinaFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);
            Aluno aluno = null;

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                String codigo = dados[0];

                // Tratamento da excecao codigo de avaliacao inexistente em sua planilha
                if (!avaliacoes.getAvaliacaoMap().containsKey(codigo)) {
                    int matriculaErro = -1;
                    ArrayList<Integer> MatriculasErro = new ArrayList<Integer>();
                    String[] matriculasStringErro = new String[10];
                    matriculasStringErro = dados[1].split(", ");

                    for (String s : matriculasStringErro) {
                        matriculaErro = Integer.parseInt(s);
                        MatriculasErro.add(matriculaErro);
                    }
                    throw new Excecao.CodAvaliacaoIndefinidoException(MatriculasErro, codigo);

                }
                // Pega codigo da avaliacao a partir do mapa de avaliacoes
                Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(codigo);
                // Pega o mapa de alunos da disciplina em que a avaliacao ocorreu
                Disciplina disciplina = disciplinas.getDisciplinaMap().get(avaliacao.getDisciplinaKey());
                AlunoMap mapaAlunos = disciplina.getAlunoMap();

                int matricula = -1;

                String doubleString = dados[2];
                doubleString = doubleString.replace(',', '.');
                double nota = Double.parseDouble(doubleString);
                if (nota < 0 || nota > 10) {
                    int matriculaErroN = -1;
                    ArrayList<Integer> MatriculasErroN = new ArrayList<Integer>();
                    String[] matriculasStringErroN = new String[10];
                    matriculasStringErroN = dados[1].split(", ");

                    for (String s : matriculasStringErroN) {
                        matriculaErroN = Integer.parseInt(s);
                        MatriculasErroN.add(matriculaErroN);
                    }
                    throw new Excecao.NotaInvalidaAvaliacaoException(MatriculasErroN, codigo, nota);
                }

                if (avaliacao instanceof Prova) {
                    matricula = Integer.parseInt(dados[1]);

                    if (alunos.containsKey(matricula) == false) {
                        throw new Excecao.MatriculaIndefinidaException(codigo, matricula);
                    }
                    if (!mapaAlunos.alunos.containsKey(matricula)) {
                        throw new Excecao.AlunoNaoMatriculadoException(matricula, codigo, avaliacao.getDisciplinaKey());
                    }

                    if(alunos.get(matricula).notasProvas.containsKey(codigo)){

                    }
                    aluno = alunos.get(matricula);
                    aluno.getNotasAvaliacoes().put(codigo, nota);

                } else {
                    ArrayList<Integer> matriculas = new ArrayList<Integer>();

                    // loop para ler as matriculas dos alunos, armazenando em uma lista
                    String[] matriculasString = new String[10];
                    matriculasString = dados[1].split(", ");

                    for (String s : matriculasString) {
                        matricula = Integer.parseInt(s);

                        // confere se o aluno esta na no mapa de alunos da disciplina
                        if (!alunos.containsKey(matricula)) {
                            throw new Excecao.MatriculaIndefinidaException(codigo, matricula);
                        }
                        if (!mapaAlunos.alunos.containsKey(matricula)) {
                            throw new Excecao.AlunoNaoMatriculadoException(matricula, codigo,
                                    avaliacao.getDisciplinaKey());
                        }

                        matriculas.add(matricula);
                    }

                    for (int m : matriculas) {
                        Aluno aluno_aluno = alunos.get(m);
                        aluno_aluno.getNotasAvaliacoes().put(codigo, nota);
                    }
                }

            }
        }

        catch (IOException e) {

            throw new Excecao("Erro de I/O");

        }

    }

}
