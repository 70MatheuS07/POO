package src;

import java.io.FileWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class Avaliacao implements Serializable {
    private String disciplinaKey;
    private String nome;
    private double peso;
    private Date data;

    public Avaliacao(String disciplina, String nome, double peso, Date data) {
        this.disciplinaKey = disciplina;
        this.nome = nome;
        this.peso = peso;
        this.data = data;
    }

    public String getDisciplinaKey() {
        return disciplinaKey;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public Date getData() {
        return data;
    }

    public abstract void ModificaMapasNotas(Map<String, Integer> qtdNotas, Map<String, Double> totalNotas,
            Avaliacao avaliacao, String key_a, double value_NA);

    public abstract void WriteKeyAvaliacao(FileWriter writer, Avaliacao avaliacao, String key_avaliacao_aluno)
            throws Excecao;

    public abstract Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(FileWriter writer, Avaliacao avaliacao,
            double value_avaliacao_aluno)
            throws Excecao;

    public class Valores_WriteValueAvaliacaoAluno {
        private double total;
        private double qtd;
        private double prova_final;

        public Valores_WriteValueAvaliacaoAluno(double total, double qtd, double prova_final) {
            this.total = total;
            this.qtd = qtd;
            this.prova_final = prova_final;
        }

        public double getTotal() {
            return total;
        }

        public double getQtd() {
            return qtd;
        }

        public double getProva_final() {
            return prova_final;
        }
    }

    public abstract Avaliacao.Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao avaliacao, double value_np);

    public abstract void TratamentoExcecoes(String[] dados, Map<Integer, Aluno> alunos,
            AlunoMap mapaAlunos,
            String codigo,
            Avaliacao avaliacao,
            double nota) throws Excecao;

}
