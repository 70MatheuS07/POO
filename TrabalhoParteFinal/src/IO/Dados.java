package src.IO;

import java.io.Serializable;

import src.aluno.AlunoMap;
import src.avaliacao.AvaliacaoMap;
import src.curso.CursoMap;
import src.disciplina.DisciplinaMap;

public class Dados implements Serializable {
    AlunoMap alunos;
    CursoMap cursos;
    DisciplinaMap disciplinas;
    AvaliacaoMap avaliacoes;

    public AlunoMap getAlunos() {
        return alunos;
    }

    public void setAlunos(AlunoMap alunos) {
        this.alunos = alunos;
    }

    public CursoMap getCursos() {
        return cursos;
    }

    public void setCursos(CursoMap cursos) {
        this.cursos = cursos;
    }

    public DisciplinaMap getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(DisciplinaMap disciplinas) {
        this.disciplinas = disciplinas;
    }

    public AvaliacaoMap getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(AvaliacaoMap avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
