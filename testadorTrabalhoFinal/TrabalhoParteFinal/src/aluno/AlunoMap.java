package src.aluno;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import src.IO.Leitura;
import src.avaliacao.Avaliacao;
import src.avaliacao.AvaliacaoMap;
import src.curso.CursoMap;
import src.disciplina.Disciplina;
import src.disciplina.DisciplinaMap;
import src.excecao.Excecao;

public class AlunoMap implements Serializable {
    public Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

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
                int matricula = Integer.parseInt(dados[0].trim());
                if (alunos.containsKey(matricula)) {
                    throw new Excecao.MatriculasIguaisException(matricula);
                }
                String nome = dados[1].trim();
                String[] disciplinasCSV = dados[2].split(",");

                // desconsidera espacos em branco
                for (int i = 0; i < disciplinasCSV.length; i++) {
                    disciplinasCSV[i] = disciplinasCSV[i].trim();
                }

                for (String disciplina : disciplinasCSV) {
                    if (!(disciplinas.getDisciplinaMap().containsKey(disciplina))) {
                        throw new Excecao.CodDisciplinaIndefinidoAlunoExcpetion(matricula, disciplina);
                    }
                }
                String tipo = dados[3].trim();

                if (tipo.equals("G")) {
                    int curso = Integer.parseInt(dados[4].trim());
                    if (!(cursos.getCursoMap().containsKey(curso))) {
                        throw new Excecao.CodCursoIndefinidoException(matricula, curso);
                    }
                    aluno = new AlunoGrad(nome, curso);
                }

                else if (tipo.equals("P")) {
                    String curso = dados[4].trim();
                    if (curso.equals("M"))
                        aluno = new AlunoPos(nome, AlunoPos.MESTRADO);

                    else if (curso.equals("D")) {
                        aluno = new AlunoPos(nome, AlunoPos.DOUTORADO);
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

            scanner.close();
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

        try (Scanner scanner = new Scanner(disciplinaFile)) {
            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                String codigo = dados[0].trim();

                // Tratamento da excecao codigo de avaliacao inexistente em sua planilha
                if (!avaliacoes.getAvaliacaoMap().containsKey(codigo)) {
                    String matriculaErro = null;
                    ArrayList<String> MatriculasErro = new ArrayList<String>();
                    String[] matriculasStringErro = new String[10];
                    matriculasStringErro = dados[1].split(",");
                    for (String s : matriculasStringErro) {
                        matriculaErro = s.trim();
                        MatriculasErro.add(matriculaErro);
                    }
                    String result = String.join(", ", MatriculasErro);
                    throw new Excecao.CodAvaliacaoIndefinidoException(result, codigo);

                }
                // Pega codigo da avaliacao a partir do mapa de avaliacoes
                Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(codigo);
                // Pega o mapa de alunos da disciplina em que a avaliacao ocorreu
                Disciplina disciplina = disciplinas.getDisciplinaMap().get(avaliacao.getDisciplinaKey());
                AlunoMap mapaAlunos = disciplina.getAlunoMap();

                String doubleString = dados[2].trim();
                doubleString = doubleString.replace(',', '.');
                double nota = Double.parseDouble(doubleString.trim());

                if (!avaliacoes.getTotalNotasIO().containsKey(codigo)) {
                    avaliacoes.getQtdNotasIO().put(codigo, 1);
                    avaliacoes.getTotalNotasIO().put(codigo, nota);

                } else {
                    int currentValueInteger = avaliacoes.getQtdNotasIO().get(codigo);
                    avaliacoes.getQtdNotasIO().put(codigo, currentValueInteger + 1);

                    double currentValueDouble = avaliacoes.getTotalNotasIO().get(codigo);
                    avaliacoes.getTotalNotasIO().put(codigo, currentValueDouble + nota);
                }

                if (nota < 0 || nota > 10) {
                    String matriculaErroN = null;
                    ArrayList<String> MatriculasErroN = new ArrayList<String>();
                    String[] matriculasStringErroN = new String[10];
                    matriculasStringErroN = dados[1].split(",");

                    for (String s : matriculasStringErroN) {
                        matriculaErroN = s.trim();
                        MatriculasErroN.add(matriculaErroN);
                    }
                    String result = String.join(",", matriculasStringErroN);

                    DecimalFormat df_media = new DecimalFormat("0.0");
                    df_media.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
                    String formattedMedia = df_media.format(nota);

                    throw new Excecao.NotaInvalidaAvaliacaoException(result, codigo, formattedMedia);
                }

                avaliacao.TratamentoExcecoes(dados, alunos, mapaAlunos, codigo, avaliacao, nota);

            }
        }

        catch (IOException e) {

            throw new Excecao("Erro de I/O");

        }

    }

}
