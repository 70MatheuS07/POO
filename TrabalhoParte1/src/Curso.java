import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    static ArrayList<Curso> cursos = new ArrayList<Curso>();
    private int codigo;
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void CadastrarCurso(Scanner scanner) {
        System.out.print("Digite o codigo do curso: ");
        this.codigo = scanner.nextInt();

        System.out.print("Digite o nome do curso: ");
        this.nome = scanner.next();
    }

}
