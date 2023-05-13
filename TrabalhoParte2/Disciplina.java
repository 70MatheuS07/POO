import java.util.Map;

public class Disciplina {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imprime os alunos de acordo com suas matrículas nas disciplinas.
     * 
     * @param cursos
     */
    public void ImprimeAlunosDisciplina(CursoMap cursos) {
        for (Map.Entry<Integer, Aluno> entry : alunos.getAlunoMap().entrySet()) {
            Aluno aluno = entry.getValue();
            Curso curso = cursos.getCursoMap().get(aluno.getCurso());
            String nomeCurso = curso.getNome();
            System.out.println("\t- " + aluno.getNome() + " (" + nomeCurso + ")");
        }
    }

}
