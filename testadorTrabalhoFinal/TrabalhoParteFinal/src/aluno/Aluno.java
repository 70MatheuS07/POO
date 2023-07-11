package src.aluno;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import src.avaliacao.AvaliacaoMap;
import src.curso.CursoMap;

public abstract class Aluno implements Serializable {
    protected String nome;
    public Map<String, Double> notasProvas = new HashMap<>();

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Map<String, Double> getNotasAvaliacoes() {
        return notasProvas;
    }

    public abstract void WriteAlunoGrad(Aluno aluno, CursoMap cursos, Map<String, Integer> alunosGeral,
            Map<String, Double> mediaAlunos,
            Map<String, Integer> alunosAprovados,
            AvaliacaoMap avaliacoes, String key_d);

}
