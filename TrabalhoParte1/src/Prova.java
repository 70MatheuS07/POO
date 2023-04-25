import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Prova {
    private static ArrayList<Prova> provas = new ArrayList<Prova>();
    private String disciplina;
    private String codigo;
    private String nome;
    private double peso;
    private Date data;

    public static ArrayList<Prova> getProvas() {
        return provas;
    }

    public static void setProvas(ArrayList<Prova> provas) {
        Prova.provas = provas;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

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
        String codigoDisciplina = new String();

        codigoDisciplina = Disciplina.SelecionaDisciplina(scanner);

        if (codigoDisciplina == null) {
            System.out.println("Nao existe disciplinas para serem escolhidas");
        } else {
            this.disciplina = codigoDisciplina;

            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

            System.out.print("Digite o codigo da prova: ");
            this.codigo = scanner.next();

            while (true) {
                System.out.print("Digite o codigo da prova: ");
                this.codigo = scanner.next();

                boolean iguais = false;

                for (int i = 0; i < provas.size(); i++) {
                    if (provas.get(i).codigo.equals(this.codigo)) {
                        iguais = true;
                        break;
                    }
                }

                if (!iguais) {
                    break;
                }

                System.out.println("\nEsse codigo ja existe, tente outro.\n");

            }

            System.out.print("Digite o nome da prova: ");
            this.codigo = scanner.next();

            System.out.print("Digite o peso da prova: ");
            this.peso = scanner.nextDouble();

            System.out.print("Digite a data da prova no formato dd/mm/yyyy:");
            String data = scanner.next();
            this.data = formatData.parse(data);
        }

        // Parei aqui

    }

}
