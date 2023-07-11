package src;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Prova extends Avaliacao {
    public static final int PARCIAL = 0;
    public static final int FINAL = 1;
    private int tipoProva;

    public Prova(String disciplina, String nome, double peso, Date data, int tipoProva) {
        super(disciplina, nome, peso, data);
        this.tipoProva = tipoProva;
    }

    public int getTipoProva() {
        return tipoProva;
    }

    @Override
    public void ModificaMapasNotas(Map<String, Integer> qtdNotas, Map<String, Double> totalNotas,
            Avaliacao avaliacao, String key_a, double value_NA) {
        if (((Prova) avaliacao).getTipoProva() == 0) {
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
    }

    @Override
    public void WriteKeyAvaliacao(FileWriter writer, Avaliacao avaliacao, String key_avaliacao_aluno) throws Excecao {

        try {
            if (((Prova) avaliacao).getTipoProva() == 0) {
                writer.write(key_avaliacao_aluno + ";");
            }
        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }

    }

    @Override
    public Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(FileWriter writer, Avaliacao avaliacao,
            double value_avaliacao_aluno)
            throws Excecao {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
        String formattedTotal = null;
        double provaFinal = -1.0;

        Valores_WriteValueAvaliacaoAluno v = null;

        try {
            if (((Prova) avaliacao).getTipoProva() == 0) {
                formattedTotal = df.format(value_avaliacao_aluno);
                writer.write(formattedTotal + ";");

                double total = value_avaliacao_aluno * avaliacao.getPeso();
                double qtd_prov_trab = avaliacao.getPeso();

                v = new Valores_WriteValueAvaliacaoAluno(total, qtd_prov_trab, -1.0);
            }

            else {
                provaFinal = value_avaliacao_aluno;
                v = new Valores_WriteValueAvaliacaoAluno(0.0, 0.0, provaFinal);
            }

            return v;

        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }
    }

    @Override
    public Avaliacao.Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao avaliacao, double value_np) {

        Avaliacao.Valores_WriteValueAvaliacaoAluno v = null;
        double prova_final = -1.0;

        if (((Prova) avaliacao).getTipoProva() == 0) {
            double total_notas = value_np * avaliacao.getPeso();
            double qtd_notas = avaliacao.getPeso();

            v = new Avaliacao.Valores_WriteValueAvaliacaoAluno(total_notas,
                    qtd_notas, -1.0);
        }

        else {
            prova_final = value_np;

            v = new Avaliacao.Valores_WriteValueAvaliacaoAluno(0.0,
                    0.0, prova_final);
        }

        return v;
    }

    @Override
    public void TratamentoExcecoes(String[] dados, Map<Integer, Aluno> alunos, AlunoMap mapaAlunos,
            String codigo,
            Avaliacao avaliacao,
            double nota) throws Excecao {

        long matriculaLong = Long.parseLong(dados[1].trim());

        // Tratamento de uma matricula fora do escopo inteiro.
        if (matriculaLong > Integer.MAX_VALUE) {
            throw new Excecao.MatriculaIndefinidaLongException(codigo, matriculaLong);
        }

        int matricula = Integer.parseInt(dados[1].trim());

        // Tratamento de uma matricula fora do escopo inteiro.
        if (alunos.containsKey(matricula) == false) {
            throw new Excecao.MatriculaIndefinidaException(codigo, matricula);
        }

        if (!mapaAlunos.alunos.containsKey(matricula)) {
            throw new Excecao.AlunoNaoMatriculadoException(matricula, codigo, avaliacao.getDisciplinaKey());
        }

        if (alunos.get(matricula).notasProvas.containsKey(codigo)) {
            throw new Excecao.NotaDuplicada(matricula, codigo);
        }
        Aluno aluno = alunos.get(matricula);
        aluno.getNotasAvaliacoes().put(codigo, nota);

    }
}
