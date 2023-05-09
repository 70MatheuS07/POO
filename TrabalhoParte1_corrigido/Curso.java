import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Curso {
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void CadastrarCurso(Map <Integer, Curso> cursos, Scanner scanner) {     
        int codigo;

        while (true) {
            System.out.print("Digite o codigo do curso: ");
            codigo = Leitura.LehInteiro(scanner);

            if (!cursos.containsKey(codigo)) {
                break;
            }

            System.out.println("\nEsse codigo ja existe, tente outro.\n");

        }

        System.out.print("Digite o nome do curso: ");
        this.nome = Leitura.LehString(scanner);

        cursos.put(codigo, this);
    }

}
