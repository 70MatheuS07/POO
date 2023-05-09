import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Prova {
    private Disciplina disciplina;
    private String nome;
    private double peso;
    private Date data;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void CadastrarProva(Scanner scanner) throws ParseException {
        String codigo;

        this.disciplina = Disciplina.SelecionaDisciplina(scanner);

        if (this.disciplina == null) {
            System.out.println("Nao existe disciplinas para serem escolhidas");
        } else {
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

            while (true) {
                System.out.print("Digite o codigo da prova: ");
                codigo = scanner.nextLine();

                if (!provas.containsKey(codigo)) {
                    break;
                }

                System.out.println("\nEsse codigo ja existe, tente outro.\n");

            }

            System.out.print("Digite o nome da prova: ");
            this.nome = scanner.nextLine();

            System.out.print("Digite o peso da prova: ");
            this.peso = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Digite a data da prova no formato dd/mm/yyyy:");
            this.data = formatData.parse(scanner.nextLine());

            provas.put(codigo, this);
        }
    }

    public static void RegistraNotaAlunoProva(Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        Aluno aluno = Aluno.getAlunos().get(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Digite o codigo da prova: ");
        String prova = scanner.nextLine();

        System.out.print("Digite a nota da prova: ");
        aluno.getNotasProvas().put(prova, scanner.nextDouble());
        scanner.nextLine();
    }

    public static void ProvasNotaRecebida() {
        System.out.println("Provas e notas recebidas:");

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        for (String key : provas.keySet()) {
            System.out.println("- " + Prova.getProvas().get(key).getDisciplina().getNome()
                    + " - " + Prova.getProvas().get(key).getNome() + " "
                    + "(" + formatador.format(Prova.getProvas().get(key).getData()) + ")");

            for (Integer key2 : Aluno.getAlunos().keySet()) {
                Set<String> keys = Aluno.getAlunos().get(key2).getNotasProvas().keySet();

                if (keys.contains(key)) {
                    System.out.print("\t- " + Aluno.getAlunos().get(key2).getNome() + ": ");
                    System.out.println(Aluno.getAlunos().get(key2).getNotasProvas().get(key));
                }
            }
        }
    }

}
