import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Curso {
    private static Map<Integer, Curso> cursos = new HashMap<Integer, Curso>();

    private String nome;

    public static Map<Integer, Curso> getCursos() {
        return cursos;
    }

    public static void setCursos(Map<Integer, Curso> cursos) {
        Curso.cursos = cursos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void CadastrarCurso(Scanner scanner) {
        int codigo;

        while (true) {
            System.out.print("Digite o codigo do curso: ");
            codigo = scanner.nextInt();

            if (!cursos.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");

        }

        System.out.print("Digite o nome do curso: ");
        this.nome = scanner.next();

        cursos.put(codigo, this);
    }

}
