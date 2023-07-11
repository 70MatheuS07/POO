package src.disciplina;

import java.io.Serializable;

import src.aluno.AlunoMap;

public class Disciplina implements Serializable {
    private String nome;
    private AlunoMap alunos = new AlunoMap();

    public AlunoMap getAlunoMap() {
        return alunos;
    }

    public void setDisciplina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
