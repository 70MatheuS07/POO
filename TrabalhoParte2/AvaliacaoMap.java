import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class AvaliacaoMap implements Serializable {
    private Map<String, Avaliacao> avaliacoes = new HashMap<String, Avaliacao>();

    public Map<String, Avaliacao> getAvaliacaoMap() {
        return avaliacoes;
    }

    /**
     * Cadastra avaliacao passada via terminal.
     * 
     * @param disciplinas
     * @param scanner
     * @throws ParseException
     */
    public void CadastrarAvaliacao(DisciplinaMap disciplinas, Scanner scanner) throws ParseException {
        String codigo;

        Disciplina disciplina = disciplinas.SelecionaDisciplina(scanner);

        if (disciplina == null) {
            System.out.println("Nao existe disciplinas para serem escolhidas");
        } else {
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

            while (true) {
                System.out.print("Digite o codigo da avaliacao: ");
                codigo = Leitura.LehLine(scanner);

                if (!avaliacoes.containsKey(codigo)) {
                    break;
                }

                System.out.println("\nEsse codigo ja existe, tente outro.\n");

            }

            System.out.print("Digite o nome da avaliacao: ");
            String nome = Leitura.LehLine(scanner);

            System.out.print("Digite o peso da avaliacao: ");
            double peso = Leitura.LehDouble(scanner);

            System.out.print("Digite a data da avaliacao no formato dd/mm/yyyy:");
            Date data = formatData.parse(Leitura.LehLine(scanner));

            System.out.println("Qual o tipo de avaliacao:\n Digite P - Prova\n Digite T - Trabalho Pratico");
            String tipoAvaliacao = Leitura.LehLine(scanner);
            
            Avaliacao avaliacao;
            boolean Booltipo;

            if (tipoAvaliacao.equals("P")) {
                System.out.println("Qual o tipo de prova:\nDigite p - Prova parcial\n Digite F - Prova final");
                String tipoProva = Leitura.LehLine(scanner);
                if (tipoProva.equals("P")) {
                    Booltipo = false;
                } else {
                    Booltipo = true;
                }
                avaliacao = new Prova(disciplina, nome, peso, data, Booltipo);
            }
            if(tipoAvaliacao.equals("T")){
                System.out.println("Digite numero maximo de alunos nesse trabalho pratico");
                int tamMax= Leitura.LehInt(scanner);
                avaliacao = new Trabalho(disciplina, nome, peso, data, tamMax);
            }

            avaliacoes.put(codigo, avaliacao);
        }
    }

    /**
     * Registra nota na avaliacao específica.
     * 
     * @param alunos
     */
    public void avaliacoesNotaRecebida(AlunoMap alunos) {
        System.out.println("avaliacoes e notas recebidas:");

        for (Map.Entry<String, Avaliacao> entry : avaliacoes.entrySet()) {
            String codigo = entry.getKey();
            Avaliacao avaliacao = entry.getValue();
            String nomeDisciplina = avaliacao.getDisciplina().getNome();
            String nomeAvaliacao = avaliacao.getNome();
            Date dataAvaliacao = avaliacao.getData();

            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

            System.out.printf("- %s - %s (%s)%n", nomeDisciplina, nomeAvaliacao, formatador.format(dataAvaliacao));

            for (Aluno aluno : alunos.getAlunoMap().values()) {
                Optional<Double> notaAvaliacao = Optional.ofNullable(aluno.getNotasAvaliacoes().get(codigo));
                notaAvaliacao.ifPresent(nota -> {
                    System.out.printf("\t- %s: %s%n", aluno.getNome(), nota);
                });
            }
        }

    }
}
