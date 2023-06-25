public class AlunoPos extends Aluno {
    public static final String MESTRADO = "Mestrado";
    public static final String DOUTORADO = "Doutorado";
    private String nivel;

    public AlunoPos(String nome ,String grad,String nivel){
        super(nome, grad);
        this.nivel=nivel;
    }
    public String getNivel(){
        return nivel;
    }
}
