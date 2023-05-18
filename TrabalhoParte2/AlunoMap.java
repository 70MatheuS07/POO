import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap implements Serializable {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    public void setAlunoMap(Map<Integer, Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * Cadastra um aluno passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarAluno(Scanner scanner) {
        int matricula;

        while (true) {
            System.out.print("Digite sua matricula: ");
            matricula = Leitura.LehInt(scanner);

            if (!alunos.containsKey(matricula)) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        String nome = Leitura.LehLine(scanner);

        
        System.out.println("Digite G - Graduacao\nDigite P - Pos graduacao");
        String PG=Leitura.LehLine(scanner);
        Aluno aluno;
        if(PG.equals("G")){
            System.out.print("Digite qual curso deseja fazer: ");
            int curso = Leitura.LehInt(scanner);
            aluno=new AlunoGrad(nome,"G", curso);
            
        }
        else{
            System.out.print("Digite M - Mestrado\n D - Doutorado ");
            String MD=Leitura.LehLine(scanner);
            if(MD.equals("M"))
                aluno=new AlunoPos(nome,"P", "Mestrado");
            
            else{
                aluno=new AlunoPos(nome,"P", "Doutorado");
            }

        }

        alunos.put(matricula, aluno);
    }

    /**
     * Registra nota do aluno no prova específica.
     * 
     * @param scanner
     */
    public void RegistraNotaAlunoProva(Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        int matricula = Leitura.LehInt(scanner);
        Aluno aluno = alunos.get(matricula);

        System.out.print("Digite o codigo da prova: ");
        String prova = Leitura.LehLine(scanner);

        System.out.print("Digite a nota da prova: ");
        double nota = Leitura.LehDouble(scanner);

        aluno.getNotasProvas().put(prova, nota);
    }

}
