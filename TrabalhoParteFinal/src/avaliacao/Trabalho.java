package src.avaliacao;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import src.aluno.Aluno;
import src.aluno.AlunoMap;
import src.excecao.Excecao;

public class Trabalho extends Avaliacao {

    int qtdGrupos;

    public Trabalho(String disciplina, String nome, double peso, Date data, int qtdGrupos) {
        super(disciplina, nome, peso, data);
        this.qtdGrupos = qtdGrupos;
    }

    @Override
    public void ModificaMapasNotas(Map<String, Integer> qtdNotas, Map<String, Double> totalNotas,
            Avaliacao avaliacao, String key_a, double value_NA) {
        if (!totalNotas.containsKey(key_a)) {
            qtdNotas.put(key_a, 1);
            totalNotas.put(key_a, value_NA);

        } else {
            int currentValueInteger = qtdNotas.get(key_a);
            qtdNotas.put(key_a, currentValueInteger + 1);

            double currentValueDouble = totalNotas.get(key_a);
            totalNotas.put(key_a, currentValueDouble + value_NA);
        }
    }

    @Override
    public void WriteKeyAvaliacao(FileWriter writer, Avaliacao avaliacao, String key_avaliacao_aluno) throws Excecao {

        try {
            writer.write(key_avaliacao_aluno + ";");
        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }

    }

    @Override
    public Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(FileWriter writer, Avaliacao avaliacao,
            double value_avaliacao_aluno)
            throws Excecao {

        try {
            DecimalFormat df = new DecimalFormat("0.00");
            df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
            String formattedTotal = null;

            // double provaFinal = value_avaliacao_aluno;

            formattedTotal = df.format(value_avaliacao_aluno);
            writer.write(formattedTotal + ";");
            double total = value_avaliacao_aluno * avaliacao.getPeso();
            double qtd_prov_trab = avaliacao.getPeso();

            Valores_WriteValueAvaliacaoAluno v = new Valores_WriteValueAvaliacaoAluno(total, qtd_prov_trab, -1.0);

            return v;

        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }

    }

    @Override
    public Avaliacao.Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao avaliacao, double value_np) {

        Avaliacao.Valores_WriteValueAvaliacaoAluno v = null;

        double total_notas = value_np * avaliacao.getPeso();
        double qtd_notas = avaliacao.getPeso();

        v = new Valores_WriteValueAvaliacaoAluno(total_notas, qtd_notas, -1.0);

        return v;
    }

    @Override
    public void TratamentoExcecoes(String[] dados, Map<Integer, Aluno> alunos, AlunoMap mapaAlunos,
            String codigo,
            Avaliacao avaliacao,
            double nota) throws Excecao {

        ArrayList<Integer> matriculas = new ArrayList<Integer>();

        // loop para ler as matriculas dos alunos, armazenando em uma lista
        String[] matriculasString = new String[10];
        matriculasString = dados[1].split(",");

        for (String s : matriculasString) {
            long matriculaLong = Long.parseLong(s.trim());

            // Tratamento de uma matricula fora do escopo inteiro.
            if (matriculaLong > Integer.MAX_VALUE) {
                throw new Excecao.MatriculaIndefinidaLongException(codigo, matriculaLong);
            }

            int matricula = Integer.parseInt(s.trim());

            if (alunos.containsKey(matricula) == false) {
                throw new Excecao.MatriculaIndefinidaException(codigo, matricula);
            }

            // confere se o aluno esta na no mapa de alunos da disciplina
            if (!alunos.containsKey(matricula)) {
                throw new Excecao.MatriculaIndefinidaException(codigo, matricula);
            }

            if (!mapaAlunos.alunos.containsKey(matricula)) {
                throw new Excecao.AlunoNaoMatriculadoException(matricula, codigo,
                        avaliacao.getDisciplinaKey());
            }
            if (alunos.get(matricula).notasProvas.containsKey(codigo)) {
                throw new Excecao.NotaDuplicada(matricula, codigo);
            }

            matriculas.add(matricula);
        }

        for (int m : matriculas) {
            Aluno aluno_aluno = alunos.get(m);
            aluno_aluno.getNotasAvaliacoes().put(codigo, nota);
        }

    }
}
