import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
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
    public void CadastrarAvaliacoes(DisciplinaMap disciplinas, String arquivo) throws Excecao, ParseException {

        File avaliacaoFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(avaliacaoFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);

            Avaliacao avaliacao = null;

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");

                String disciplina = dados[0];
                String codigo = dados[1];
                String nome = dados[2];
                double peso = Double.parseDouble(dados[3]);
                String tipo = dados[4];
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formatter.parse(dados[5]);

                if (tipo.equals("P")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, false);

                } else if (tipo.equals("F")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, true);
                }

                else if (tipo.equals("T")) {
                    int tamMax = Integer.parseInt(dados[6]);
                    avaliacao = new Trabalho(disciplina, nome, peso, data, tamMax);
                }

                System.out.printf("%s %s %s %f %s %s\n", disciplina, codigo, nome, peso, tipo, data);

                avaliacoes.put(codigo, avaliacao);
            }
        }

        catch (FileNotFoundException e) {

            throw new Excecao("Arquivo não encontrado");

        }
    }

    /**
     * Registra nota na avaliacao específica.
     * 
     * @param alunos
     */
    public void avaliacoesNotaRecebida(AlunoMap alunos, DisciplinaMap d) {
        System.out.println("avaliacoes e notas recebidas:");

        for (Map.Entry<String, Avaliacao> entry : avaliacoes.entrySet()) {
            String codigo = entry.getKey();
            Avaliacao avaliacao = entry.getValue();
            String nomeDisciplina = d.getDisciplinaMap().get(avaliacao.getDisciplinaKey()).getNome();
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
