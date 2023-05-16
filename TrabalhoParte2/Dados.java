import java.io.Serializable;

public class Dados implements Serializable {
    AlunoMap alunos;
    CursoMap cursos;
    DisciplinaMap disciplinas;
    ProvaMap provas;

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

    public ProvaMap getProvas() {
        return provas;
    }

    public void setProvas(ProvaMap provas) {
        this.provas = provas;
    }
}
