import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ProvaMap {
    private Map<String, Prova> provas = new HashMap<String, Prova>();

    public Map<String, Prova> getProvaMap() {
        return provas;
    }

    public void CadastrarProva(DisciplinaMap disciplinas, Scanner scanner) throws ParseException {
        String codigo;

        Disciplina disciplina = disciplinas.SelecionaDisciplina(scanner);

        if (disciplina == null) {
            System.out.println("Nao existe disciplinas para serem escolhidas");
        } else {
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

            while (true) {
                System.out.print("Digite o codigo da prova: ");
                codigo = Leitura.LehLine(scanner);

                if (!provas.containsKey(codigo)) {
                    break;
                }

                System.out.println("\nEsse codigo ja existe, tente outro.\n");

            }

            Prova prova = new Prova();

            System.out.print("Digite o nome da prova: ");
            String nome = Leitura.LehLine(scanner);

            System.out.print("Digite o peso da prova: ");
            double peso = Leitura.LehDouble(scanner);

            System.out.print("Digite a data da prova no formato dd/mm/yyyy:");
            Date data = formatData.parse(Leitura.LehLine(scanner));

            prova.setProva(nome, peso, data);

            provas.put(codigo, prova);
        }
    }

    public void ProvasNotaRecebida(AlunoMap alunos) {
        System.out.println("Provas e notas recebidas:");

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        for (String key : provas.keySet()) {
            System.out.println("- " + provas.get(key).getDisciplina().getNome()
                    + " - " + provas.get(key).getNome() + " "
                    + "(" + formatador.format(provas.get(key).getData()) + ")");

            for (Integer key2 : alunos.getAlunoMap().keySet()) {
                Set<String> keys = alunos.getAlunoMap().get(key2).getNotasProvas().keySet();

                if (keys.contains(key)) {
                    System.out.print("\t- " + alunos.getAlunoMap().get(key2).getNome() + ": ");
                    System.out.println(alunos.getAlunoMap().get(key2).getNotasProvas().get(key));
                }
            }
        }
    }
}
