import java.util.ArrayList;
import java.util.Scanner;

public class Curso {
    private static ArrayList<Curso> cursos = new ArrayList<Curso>();
    private int codigo;
    private String nome;

    public static ArrayList<Curso> getCursos() {
        return cursos;
    }

    public static void setCursos(ArrayList<Curso> cursos) {
        Curso.cursos = cursos;
    }

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
        while (true) {
            System.out.print("Digite o codigo do curso: ");
            this.codigo = scanner.nextInt();

            boolean iguais = false;

            for (int i = 0; i < cursos.size(); i++) {
                if (cursos.get(i).codigo == this.codigo) {
                    iguais = true;
                    break;
                }
            }

            if (!iguais) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");

        }

        System.out.print("Digite o nome do curso: ");
        this.nome = scanner.next();
    }

}
