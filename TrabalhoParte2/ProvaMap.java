import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ProvaMap implements Serializable {
    private Map<String, Prova> provas = new HashMap<String, Prova>();

    public Map<String, Prova> getProvaMap() {
        return provas;
    }

    /**
     * Cadastra prova passada via terminal.
     * 
     * @param disciplinas
     * @param scanner
     * @throws ParseException
     */
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

            prova.setProva(disciplina, nome, peso, data);

            provas.put(codigo, prova);
        }
    }

    /**
     * Registra nota na prova específica.
     * 
     * @param alunos
     */
    public void ProvasNotaRecebida(AlunoMap alunos) {
        System.out.println("Provas e notas recebidas:");

        for (Map.Entry<String, Prova> entry : provas.entrySet()) {
            String codigo = entry.getKey();
            Prova prova = entry.getValue();
            String nomeDisciplina = prova.getDisciplina().getNome();
            String nomeProva = prova.getNome();
            Date dataProva = prova.getData();

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            System.out.printf("- %s - %s (%s)%n", nomeDisciplina, nomeProva, formatador.format(dataProva));

            for (Aluno aluno : alunos.getAlunoMap().values()) {
                Optional<Double> notaProva = Optional.ofNullable(aluno.getNotasProvas().get(codigo));
                notaProva.ifPresent(nota -> {
                    System.out.printf("\t- %s: %s%n", aluno.getNome(), nota);
                });
            }
        }

    }
}
