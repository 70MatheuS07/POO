import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CursoMap {
    private Map<Integer, Curso> cursos = new HashMap<Integer, Curso>();

    public Map<Integer, Curso> getCursoMap() {
        return cursos;
    }

    public void CadastrarCurso(Scanner scanner) {
        int codigo;

        Curso curso = new Curso();

        while (true) {
            System.out.print("Digite o codigo do curso: ");
            codigo = Leitura.LehInt(scanner);

            if (!cursos.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");

        }

        System.out.print("Digite o nome do curso: ");
        curso.setCurso(Leitura.LehLine(scanner));

        cursos.put(codigo, curso);
    }
}
