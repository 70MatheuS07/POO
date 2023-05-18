public class AlunoGrad extends Aluno{
    private int curso;
    
    public AlunoGrad(String nome , String grad,int curso){
        super(nome, grad);
        this.curso=curso;
    }
    public void setAluno(String nome, int curso) {
        this.nome = nome;
        this.curso = curso;
    }


    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
}