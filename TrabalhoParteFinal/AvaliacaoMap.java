import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

                if(!(disciplinas.getDisciplinaMap().containsKey(disciplina))) {
                    throw new Excecao.CodDisciplinaIndefinidoAvalExcpetion(codigo,disciplina);
                }

                String nome = dados[2];
                double peso = Double.parseDouble(dados[3]);

                if(peso<=0){
                   throw new Excecao.PesoZeroNegativo(codigo, peso);
                }

                String tipo = dados[4];
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formatter.parse(dados[5]);

                int tamMax;
                if((tipo.equals("P") || tipo.equals("F"))&& dados.length>6){
                    tamMax = Integer.parseInt(dados[6]);
                    throw new Excecao.TamGrupoNaProvaException(codigo, tamMax);
                }

                if (tipo.equals("P")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, false);

                } else if (tipo.equals("F")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, true);
                }

                else if (tipo.equals("T")) {
                    tamMax = Integer.parseInt(dados[6]);
                    if(tamMax<=0){
                        throw new Excecao.TamMaxZeroNegativo(codigo, tamMax);
                    }
                    avaliacao = new Trabalho(disciplina, nome, peso, data, tamMax);
                }
                else{
                    throw new Excecao.NemPNemTException(codigo, tipo);
                }

                avaliacoes.put(codigo, avaliacao);
            }
            //confere se há alguma disciplina sem avaliações cadastrada
            for(Map.Entry<String, Disciplina> entry : disciplinas.getDisciplinaMap().entrySet()){
                boolean bool=false;
                String CodD= entry.getKey();
                for(Map.Entry<String, Avaliacao> entry2 : avaliacoes.entrySet()){
                    Avaliacao avaliacaoCompare=entry2.getValue();
                    String CodDCompare= avaliacaoCompare.getDisciplinaKey();
                    if(CodDCompare.equals(CodD)){
                        bool=true;
                        break;
                    }
                }
                if(!bool){
                    throw new Excecao.DisciplinaSemAvaliacaoException(CodD);
                }
            }
        }

        catch (IOException e) {

            throw new Excecao.ErroDeIO();

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

    public void CriaAvaliacoesCSV(DisciplinaMap disciplinas, AlunoMap alunos) throws Excecao{

        try {
            List<Map.Entry<String, Avaliacao>> entries = new ArrayList<>(avaliacoes.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<String, Avaliacao>>() {
                @Override
                public int compare(Map.Entry<String, Avaliacao> a, Map.Entry<String, Avaliacao> b) {
                    if (a.getValue().getDisciplinaKey().equals(b.getValue().getDisciplinaKey())) {
                        return a.getKey().compareTo(b.getKey());
                    }

                    return a.getValue().getDisciplinaKey().compareTo(b.getValue().getDisciplinaKey());
                }
            });

            Map<String, Integer> qtdNotas = new HashMap<String, Integer>();
            Map<String, Double> totalNotas = new HashMap<String, Double>();

            FileWriter writer = new FileWriter("3-avaliacoes.csv");

            writer.write("Disciplina;Código;Avaliação;Data;Média\n");

            for (Map.Entry<String, Avaliacao> a : entries) {
                String key_a = a.getKey();

                for (Map.Entry<Integer, Aluno> aluno : alunos.getAlunoMap().entrySet()) {
                    Aluno value_aluno = aluno.getValue();

                    for (Map.Entry<String, Double> na : value_aluno.getNotasAvaliacoes().entrySet()) {
                        String key_NA = na.getKey();
                        Double value_NA = na.getValue();

                        if (key_a.equals(key_NA)) {
                            Avaliacao avaliacao = avaliacoes.get(key_NA);

                            if (avaliacao instanceof Prova) {
                                if (!((Prova) avaliacao).getTipoProva()) {
                                    if (!totalNotas.containsKey(key_a)) {
                                        qtdNotas.put(key_a, 1);
                                        totalNotas.put(key_a, value_NA);

                                    } else {
                                        int currentValueInteger = qtdNotas.get(key_a);
                                        qtdNotas.put(key_a, currentValueInteger + 1);

                                        double currentValueDouble = totalNotas.get(key_a);
                                        totalNotas.put(key_a, currentValueDouble + value_NA);
                                    }
                                }

                            } else {
                                if (!totalNotas.containsKey(key_a)) {
                                    qtdNotas.put(key_a, 1);
                                    totalNotas.put(key_a, value_NA);

                                } else {
                                    int currentValueInteger = qtdNotas.get(key_a);
                                    qtdNotas.put(key_a, currentValueInteger + 1);

                                    double currentValueDouble = totalNotas.get(key_a);
                                    totalNotas.put(key_a, currentValueDouble + value_NA);
                                }
                            }

                        }

                    }

                }
            }

            List<Map.Entry<String, Double>> entries2 = new ArrayList<>(totalNotas.entrySet());
            Collections.sort(entries2, new Comparator<Map.Entry<String, Double>>() {
                @Override
                public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                    if (avaliacoes.get(a.getKey()).getDisciplinaKey()
                            .equals(avaliacoes.get(b.getKey()).getDisciplinaKey())) {
                        return a.getKey().compareTo(b.getKey());
                    }
                    return avaliacoes.get(a.getKey()).getDisciplinaKey()
                            .compareTo(avaliacoes.get(b.getKey()).getDisciplinaKey());
                }
            });

            for (Map.Entry<String, Double> entry : entries2) {
                String key_entry = entry.getKey();
                double value_entry = entry.getValue();

                String codigo = key_entry;
                double media = ((double) value_entry / qtdNotas.get(key_entry));

                DecimalFormat df_media = new DecimalFormat("0.00");
                df_media.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
                String formattedMedia = df_media.format(media);

                Avaliacao avaliacao = avaliacoes.get(key_entry);

                String disciplinaAvaliacao = avaliacao.getDisciplinaKey();
                String nomeAvaliacao = avaliacao.getNome();

                Date dataAvaliacao = avaliacao.getData();

                SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
                String dia = sdff.format(dataAvaliacao);

                writer.write(disciplinaAvaliacao + ";");
                writer.write(codigo + ";");
                writer.write(nomeAvaliacao + ";");
                writer.write(dia + ";");
                writer.write(formattedMedia + "\n");

            }

            writer.close();

        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }

    }
}
