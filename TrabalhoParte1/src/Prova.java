import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prova {
    private static Map<String, Prova> provas = new HashMap<String, Prova>();
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
                codigo = scanner.next();

                if (!provas.containsKey(codigo)) {
                    break;
                }

                System.out.println("\nEsse codigo ja existe, tente outro.\n");

            }

            System.out.print("Digite o nome da prova: ");
            this.nome = scanner.next();

            System.out.print("Digite o peso da prova: ");
            this.peso = scanner.nextDouble();

            System.out.print("Digite a data da prova no formato dd/mm/yyyy:");
            this.data = formatData.parse(scanner.next());

            provas.put(codigo, this);
        }
    }

    public static void RegistraNotaAlunoProva(Scanner scanner) {
        Aluno aluno = Aluno.getAlunos().get(scanner.nextInt());
        String prova = scanner.next();

        aluno.getNotasProvas().put(prova, scanner.nextDouble());
    }

}
