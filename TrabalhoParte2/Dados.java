import java.io.Serializable;

public class Dados implements Serializable {
    AlunoMap alunos;
    CursoMap cursos;
    DisciplinaMap disciplinas;
    AvaliacaoMap provas;

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
        return provas;
    }

    public void setAvaliacoes(AvaliacaoMap provas) {
        this.provas = provas;
    }
}
