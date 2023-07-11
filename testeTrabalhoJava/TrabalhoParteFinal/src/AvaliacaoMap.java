package src;

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

                String disciplina = dados[0].trim();
                String codigo = dados[1].trim();

                if (!(disciplinas.getDisciplinaMap().containsKey(disciplina))) {
                    throw new Excecao.CodDisciplinaIndefinidoAvalExcpetion(codigo, disciplina);
                }

                String nome = dados[2].trim();
                double peso = Double.parseDouble(dados[3].trim());

                if (peso <= 0) {
                    throw new Excecao.PesoZeroNegativo(codigo, peso);
                }

                String tipo = dados[4].trim();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formatter.parse(dados[5]);

                int tamMax;
                if ((tipo.equals("P") || tipo.equals("F")) && dados.length > 6) {
                    tamMax = Integer.parseInt(dados[6].trim());
                    throw new Excecao.TamGrupoNaProvaException(codigo, tamMax);
                }

                if (tipo.equals("P")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, Prova.PARCIAL);

                } else if (tipo.equals("F")) {
                    avaliacao = new Prova(disciplina, nome, peso, data, Prova.FINAL);
                }

                else if (tipo.equals("T")) {
                    tamMax = Integer.parseInt(dados[6]);
                    if (tamMax <= 0) {
                        throw new Excecao.TamMaxZeroNegativo(codigo, tamMax);
                    }
                    avaliacao = new Trabalho(disciplina, nome, peso, data, tamMax);
                } else {
                    throw new Excecao.NemPNemTException(codigo, tipo);
                }

                avaliacoes.put(codigo, avaliacao);
            }
            // confere se há alguma disciplina sem avaliações cadastrada
            for (Map.Entry<String, Disciplina> entry : disciplinas.getDisciplinaMap().entrySet()) {
                boolean bool = false;
                String CodD = entry.getKey();
                for (Map.Entry<String, Avaliacao> entry2 : avaliacoes.entrySet()) {
                    Avaliacao avaliacaoCompare = entry2.getValue();
                    String CodDCompare = avaliacaoCompare.getDisciplinaKey();
                    if (CodDCompare.equals(CodD)) {
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    throw new Excecao.DisciplinaSemAvaliacaoException(CodD);
                }
            }

            scanner.close();
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

    /**
     * Cria o arquivo avaliacoes.csv
     * 
     * @param disciplinas
     * @param alunos
     * @throws Excecao
     */
    public void CriaAvaliacoesCSV(DisciplinaMap disciplinas, AlunoMap alunos) throws Excecao {

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

                            // Tiramos o instanceof
                            avaliacao.ModificaMapasNotas(qtdNotas, totalNotas, avaliacao, key_a, value_NA);

                        }

                    }

                }
            }

            File disciplinaFile = new File("notas.csv");

            Map<String, Integer> qtdNotasIO = new HashMap<String, Integer>();
            Map<String, Double> totalNotasIO = new HashMap<String, Double>();

            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                String codigo = dados[0].trim();

                String doubleString = dados[2].trim();
                doubleString = doubleString.replace(',', '.');
                double nota = Double.parseDouble(doubleString);

                if (!totalNotasIO.containsKey(codigo)) {
                    qtdNotasIO.put(codigo, 1);
                    totalNotasIO.put(codigo, nota);

                } else {
                    int currentValueInteger = qtdNotasIO.get(codigo);
                    qtdNotasIO.put(codigo, currentValueInteger + 1);

                    double currentValueDouble = totalNotasIO.get(codigo);
                    totalNotasIO.put(codigo, currentValueDouble + nota);
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

                if (totalNotasIO.containsKey(key_entry)) {

                    String codigo = key_entry;
                    double media = ((double) totalNotasIO.get(key_entry) / qtdNotasIO.get(key_entry));

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

            }

            writer.close();
            scanner.close();

        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }

    }
}
