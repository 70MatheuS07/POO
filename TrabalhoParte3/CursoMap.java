import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CursoMap implements Serializable{
    private Map<Integer, Curso> cursos = new HashMap<Integer, Curso>();

    public Map<Integer, Curso> getCursoMap() {
        return cursos;
    }

    /**
     * Cadastra o curso passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarCurso(Scanner scanner) throws Excecao {
        int codigo;
        while (true) {
            System.out.print("Digite o codigo do curso: ");
            codigo = Leitura.LehInt(scanner);

            if (!cursos.containsKey(codigo)) {
                break;
            }

            throw new Excecao.CodigosIguaisException(codigo);

        }

        System.out.print("Digite o nome do curso: ");

        String nome = Leitura.LehLine(scanner);

        Curso curso = new Curso();

        curso.setCurso(nome);

        cursos.put(codigo, curso);
    }
}
