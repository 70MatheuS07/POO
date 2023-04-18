import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Prova {
    static ArrayList<Prova> provas = new ArrayList<Prova>();
    private String disciplina;
    private String codigo;
    private String nome;
    private double peso;
    private Date data;

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

    public void CadastrarProva(Scanner scanner) {
        this.disciplina = Disciplina.SelecionaDisciplina(scanner);

        System.out.print("Digite o codigo da prova: ");
        this.codigo = scanner.next();

        System.out.print("Digite o nome da prova: ");
        this.codigo = scanner.next();

        System.out.print("Digite o peso da prova: ");
        this.peso = scanner.nextDouble();

        System.out.print("Digite a data da prova no formato dd/mm/yyyy: ");
        String data = scanner.next();
        this.data = data.parse();

        //Parei aqui

    }
}
