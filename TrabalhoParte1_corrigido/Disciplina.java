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

    public void ImprimeAlunosDisciplina(CursoMap cursos) {
        for (int i = 0; i < alunos.getAlunoMap().size(); i++) {
            System.out.println("\t- " + alunos.getAlunoMap().get(i).getNome() +
                    " (" + cursos.getCursoMap().get(alunos.getAlunoMap().get(i).getCurso()).getNome()
                    + ")");
        }
    }

}
