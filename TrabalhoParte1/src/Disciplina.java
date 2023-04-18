import java.util.ArrayList;
import java.util.Scanner;

public class Disciplina {
    static ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private String codigo;
    private String nome;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void CadastrarDisciplina(Scanner scanner) {
        System.out.print("Digite o codigo da disciplina: ");
        this.codigo = scanner.next();

        System.out.print("Digite o nome da disciplina: ");
        this.nome = scanner.next();
    }

    public static String SelecionaDisciplina(Scanner scanner) {
        if (disciplinas.size() < 1) {
            return null;
        }

        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disciplina = disciplinas.get(i);
            System.out.print((i + 1) + " " + disciplina.nome + " - " + disciplina.codigo);
        }

        int num = 0;

        while (true) {
            System.out.print("Digite o numero da disciplina para a prova: ");
            num = scanner.nextInt();

            if (num > 0 || num <= disciplinas.size()) {
                break;
            } else {
                System.out.println("Digite um numero valido.");
                System.out.println();
            }
        }

        return disciplinas.get(num - 1).codigo;

    }

}
