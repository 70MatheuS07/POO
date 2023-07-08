package src;
public class AlunoGrad extends Aluno{
    private int curso;
    
    public AlunoGrad(String nome , String grad,int curso){
        super(nome, grad);
        this.curso=curso;
    }

    public int getCurso() {
        return curso;
    }
}