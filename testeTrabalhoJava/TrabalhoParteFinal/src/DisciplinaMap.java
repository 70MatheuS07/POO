package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class DisciplinaMap implements Serializable {
    private Map<String, Disciplina> disciplinas = new HashMap<String, Disciplina>();

    public Map<String, Disciplina> getDisciplinaMap() {
        return disciplinas;
    }

    /**
     * Cadastra disciplina passada pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarDisciplinas(String arquivo) throws Excecao {
        File disciplinaFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                String codigo = dados[0].trim();
                String nome = dados[1].trim();

                Disciplina disciplina = new Disciplina();

                disciplina.setDisciplina(nome);
                disciplinas.put(codigo, disciplina);
            }

            scanner.close();
        }

        catch (IOException e) {

            throw new Excecao("Erro de I/O");

        }
    }

    /**
     * Matricula aluno na disciplina. O aluno é selecionado pela sua matricula
     * e a disciplina pelo seu código. Tudo via terminal.
     * 
     * @param alunos
     * @param scanner
     */
    public void MatricularAlunoDisciplina(AlunoMap alunos, Scanner scanner) {
        System.out.print("Digite a matricula do aluno: ");
        int matricula = Leitura.LehInt(scanner);
        Aluno aluno = alunos.getAlunoMap().get(matricula);

        System.out.print("Digite o codigo da disciplina: ");
        String disciplina = Leitura.LehLine(scanner);

        disciplinas.get(disciplina).getAlunoMap().getAlunoMap().put(matricula, aluno);
    }

    /**
     * Imprime as disciplinas com seus alunos matriculados.
     * 
     * @param cursos
     */
    public void DisciplinasAlunosMatriculados(CursoMap cursos) {
        System.out.println("Disciplinas e alunos matriculados:");

        for (Map.Entry<String, Disciplina> entry : disciplinas.entrySet()) {
            String codigo = entry.getKey();
            Disciplina disciplina = entry.getValue();
            String nome = disciplina.getNome();

            System.out.printf("- %s (%s)%n", nome, codigo);

            disciplina.ImprimeAlunosDisciplina(cursos);
            System.out.println(); // adiciona uma linha em branco entre as disciplinas
        }
    }

    /**
     * Imprime para debuggar.
     */
    public void ImprimeDisciplinaCSV() {
        for (Map.Entry<String, Disciplina> entry : disciplinas.entrySet()) {
            String key = entry.getKey();
            Disciplina value = entry.getValue();

            System.out.printf("disciplina: %s %s\n", key, value.getNome());

            for (Map.Entry<Integer, Aluno> entry2 : value.getAlunoMap().getAlunoMap().entrySet()) {
                int key2 = entry2.getKey();
                Aluno value2 = entry2.getValue();

                System.out.printf("aluno: %d %s\n", key2, value2.getNome());
            }
        }
    }

    public void CriaPautaDisciplinas(AlunoMap alunos, AvaliacaoMap avaliacoes) throws Excecao {
        try {
            double total = 0.0;
            int qtd_prov_trab = 0;

            for (Map.Entry<String, Disciplina> d : disciplinas.entrySet()) {
                String key_d = d.getKey();
                Disciplina value_d = d.getValue();

                FileWriter writer = new FileWriter("1-pauta-" + key_d + ".csv");

                writer.write("Matrícula;Aluno;");

                Map<Integer, Aluno> aluno_map = value_d.getAlunoMap().getAlunoMap();

                // Esse for é para imprimir todas as chaves de avaliação corretas
                for (Map.Entry<Integer, Aluno> entry_aluno : aluno_map.entrySet()) {
                    Aluno value_a = entry_aluno.getValue();

                    Map<String, Double> avaliacoes_aluno = value_a.getNotasAvaliacoes();

                    List<Map.Entry<String, Double>> entries = new ArrayList<>(avaliacoes_aluno.entrySet());
                    Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
                        @Override
                        public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                            return a.getKey().compareTo(b.getKey());
                        }
                    });

                    for (Map.Entry<String, Double> entry_ava : entries) {
                        String key_avaliacao_aluno = entry_ava.getKey();

                        Avaliacao aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                        if (aa.getDisciplinaKey().equals(key_d)) {
                            if (aa instanceof Prova) {
                                if (((Prova) aa).getTipoProva() == 0) {
                                    writer.write(key_avaliacao_aluno + ";");
                                }

                            } else {
                                writer.write(key_avaliacao_aluno + ";");
                            }

                        }
                    }

                    break;
                }

                writer.write("Média Parcial;Prova Final;Média Final\n");

                // Adicione valores ao mapa
                List<Map.Entry<Integer, Aluno>> entries = new ArrayList<>(aluno_map.entrySet());
                Collections.sort(entries, new Comparator<Map.Entry<Integer, Aluno>>() {

                    @Override
                    public int compare(Map.Entry<Integer, Aluno> a, Map.Entry<Integer, Aluno> b) {
                        return a.getValue().getNome().compareTo(b.getValue().getNome());
                    }
                });

                for (Map.Entry<Integer, Aluno> a : entries) {
                    int key_a = a.getKey();
                    Aluno value_a = a.getValue();
                    total = 0.0;
                    qtd_prov_trab = 0;
                    double provaFinal = -1.0;

                    writer.write(key_a + ";" + value_a.getNome() + ";");

                    Map<String, Double> avaliacoesAluno = value_a.getNotasAvaliacoes();

                    DecimalFormat df = new DecimalFormat("0.00");
                    df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
                    String formattedTotal = null;

                    List<Map.Entry<String, Double>> entries2 = new ArrayList<>(avaliacoesAluno.entrySet());
                    Collections.sort(entries2, new Comparator<Map.Entry<String, Double>>() {
                        @Override
                        public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                            return a.getKey().compareTo(b.getKey());
                        }
                    });

                    for (Map.Entry<String, Double> avaliacao_aluno : entries2) {
                        String key_avaliacao_aluno = avaliacao_aluno.getKey();
                        double value_avaliacao_aluno = avaliacao_aluno.getValue();

                        Avaliacao aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                        if (aa.getDisciplinaKey().equals(key_d)) {
                            if (aa instanceof Prova) {
                                if (((Prova) aa).getTipoProva() == 0) {
                                    formattedTotal = df.format(value_avaliacao_aluno);
                                    writer.write(formattedTotal + ";");
                                    total += value_avaliacao_aluno * aa.getPeso();
                                    qtd_prov_trab += aa.getPeso();
                                }

                                else {
                                    provaFinal = value_avaliacao_aluno;
                                }

                            } else {
                                formattedTotal = df.format(value_avaliacao_aluno);
                                writer.write(formattedTotal + ";");
                                total += value_avaliacao_aluno * aa.getPeso();
                                qtd_prov_trab += aa.getPeso();
                            }
                        }
                    }

                    total = ((double) total / qtd_prov_trab);

                    formattedTotal = df.format(total);

                    writer.write(formattedTotal + ";");

                    if (provaFinal >= 0.0) {
                        formattedTotal = df.format(provaFinal);
                        writer.write(formattedTotal + ";");
                        total = ((double) (total + provaFinal) / 2);

                        formattedTotal = df.format(total);
                        writer.write(formattedTotal);
                    } else {
                        writer.write("-;");
                        formattedTotal = df.format(total);
                        writer.write(formattedTotal);
                    }

                    writer.write("\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }
    }

    public void CriaDisciplinasCSV(AlunoMap alunos, AvaliacaoMap avaliacoes, CursoMap cursos) throws Excecao {
        try {
            FileWriter writer = new FileWriter("2-disciplinas.csv");
            writer.write("Código;Disciplina;Curso;Média;% Aprovados\n");
            for (Map.Entry<String, Disciplina> d : disciplinas.entrySet()) {
                String key_d = d.getKey();
                Disciplina value_d = d.getValue();

                Map<String, Integer> alunosGeral = new HashMap<String, Integer>();
                Map<String, Double> mediaAlunos = new HashMap<String, Double>();
                Map<String, Integer> alunosAprovados = new HashMap<String, Integer>();

                for (Map.Entry<Integer, Aluno> a : value_d.getAlunoMap().getAlunoMap().entrySet()) {
                    Aluno value_a = a.getValue();
                    double total_notas = 0.0;
                    int qtd_notas = 0;
                    double total_final = 0.0;
                    double prova_final = 0.0;

                    if (value_a instanceof AlunoGrad) {
                        AlunoGrad aluno_grad = (AlunoGrad) value_a;

                        Curso curso = cursos.getCursoMap().get(aluno_grad.getCurso());

                        if (!alunosGeral.containsKey(curso.getNome())) {
                            alunosGeral.put(curso.getNome(), 1);
                            mediaAlunos.put(curso.getNome(), 0.0);
                            alunosAprovados.put(curso.getNome(), 0);
                        } else {
                            int currentValue = alunosGeral.get(curso.getNome());
                            alunosGeral.put(curso.getNome(), currentValue + 1);
                        }

                        // Preciso saber se ele foi aprovado.
                        for (Map.Entry<String, Double> np : aluno_grad.notasProvas.entrySet()) {
                            String key_np = np.getKey();
                            double value_np = np.getValue();

                            Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(key_np);

                            if (avaliacao.getDisciplinaKey().equals(key_d)) {
                                if (avaliacao instanceof Prova) {
                                    if (((Prova) avaliacao).getTipoProva() == 0) {
                                        total_notas += value_np * avaliacao.getPeso();
                                        qtd_notas += avaliacao.getPeso();
                                    }

                                    else {
                                        prova_final = value_np;
                                    }

                                } else {
                                    total_notas += value_np * avaliacao.getPeso();
                                    qtd_notas += avaliacao.getPeso();
                                }
                            }
                        }

                        total_final = ((double) (total_notas / qtd_notas));

                        if (total_final >= 7.0) {
                            int currentValueAprovados = alunosAprovados.get(curso.getNome());
                            alunosAprovados.put(curso.getNome(), currentValueAprovados + 1);
                        } else {
                            total_final = ((double) ((total_final + prova_final) / 2));

                            if (total_final >= 5.0) {
                                int currentValueAprovados = alunosAprovados.get(curso.getNome());
                                alunosAprovados.put(curso.getNome(), currentValueAprovados + 1);
                            }

                        }

                        double currentValueDouble = mediaAlunos.get(curso.getNome());
                        mediaAlunos.put(curso.getNome(), currentValueDouble + total_final);

                    } else {
                        AlunoPos aluno_pos = (AlunoPos) value_a;

                        if (aluno_pos.getNivel() == AlunoPos.MESTRADO) {
                            if (!alunosGeral.containsKey("Mestrado")) {
                                alunosGeral.put("Mestrado", 1);
                                mediaAlunos.put("Mestrado", 0.0);
                                alunosAprovados.put("Mestrado", 0);
                            } else {
                                int currentValue = alunosGeral.get("Mestrado");
                                alunosGeral.put("Mestrado", currentValue + 1);
                            }

                            // Preciso saber se ele foi aprovado.
                            for (Map.Entry<String, Double> np : aluno_pos.getNotasAvaliacoes().entrySet()) {
                                String key_np = np.getKey();
                                double value_np = np.getValue();

                                Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(key_np);

                                if (avaliacao.getDisciplinaKey().equals(key_d)) {
                                    if (avaliacao instanceof Prova) {
                                        if (((Prova) avaliacao).getTipoProva() == 0) {
                                            total_notas += value_np * avaliacao.getPeso();
                                            qtd_notas += avaliacao.getPeso();
                                        }

                                        else {
                                            prova_final = value_np;
                                        }

                                    } else {
                                        total_notas += value_np * avaliacao.getPeso();
                                        qtd_notas += avaliacao.getPeso();
                                    }
                                }
                            }

                            total_final = ((double) (total_notas / qtd_notas));

                            if (total_final >= 7.0) {
                                int currentValueAprovados = alunosAprovados.get("Mestrado");
                                alunosAprovados.put("Mestrado", currentValueAprovados + 1);
                            } else {
                                total_final = ((double) ((total_final + prova_final) / 2));

                                if (total_final >= 5.0) {
                                    int currentValueAprovados = alunosAprovados.get("Mestrado");
                                    alunosAprovados.put("Mestrado", currentValueAprovados + 1);
                                }

                            }

                            double currentValueDouble = mediaAlunos.get("Mestrado");
                            mediaAlunos.put("Mestrado", currentValueDouble + total_final);

                        }

                        else {
                            if (!alunosGeral.containsKey("Doutorado")) {
                                alunosGeral.put("Doutorado", 1);
                                mediaAlunos.put("Doutorado", 0.0);
                                alunosAprovados.put("Doutorado", 0);
                            } else {
                                int currentValue = alunosGeral.get("Doutorado");
                                alunosGeral.put("Doutorado", currentValue + 1);
                            }

                            // Preciso saber se ele foi aprovado.
                            for (Map.Entry<String, Double> np : aluno_pos.getNotasAvaliacoes().entrySet()) {
                                String key_np = np.getKey();
                                double value_np = np.getValue();

                                Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(key_np);

                                if (avaliacao.getDisciplinaKey().equals(key_d)) {
                                    if (avaliacao instanceof Prova) {
                                        if (((Prova) avaliacao).getTipoProva() == 0) {
                                            total_notas += value_np * avaliacao.getPeso();
                                            qtd_notas += avaliacao.getPeso();
                                        }

                                        else {
                                            prova_final = value_np;
                                        }

                                    } else {
                                        total_notas += value_np * avaliacao.getPeso();
                                        qtd_notas += avaliacao.getPeso();
                                    }
                                }
                            }

                            total_final = ((double) (total_notas / qtd_notas));

                            if (total_final >= 7.0) {
                                int currentValueAprovados = alunosAprovados.get("Doutorado");
                                alunosAprovados.put("Doutorado", currentValueAprovados + 1);
                            } else {
                                total_final = ((double) ((total_final + prova_final) / 2));

                                if (total_final >= 5.0) {
                                    int currentValueAprovados = alunosAprovados.get("Doutorado");
                                    alunosAprovados.put("Doutorado", currentValueAprovados + 1);
                                }

                            }

                            double currentValueDouble = mediaAlunos.get("Doutorado");
                            mediaAlunos.put("Doutorado", currentValueDouble + total_final);

                        }

                    }

                }

                for (Map.Entry<String, Integer> aluno_geral : alunosGeral.entrySet()) {
                    String key_ag = aluno_geral.getKey();
                    int value_ag = aluno_geral.getValue();

                    writer.write(key_d + ";" + value_d.getNome() + ";" + key_ag + ";");

                    double total = mediaAlunos.get(key_ag);
                    double media = ((double) (total / value_ag));
                    double aprovados = (((double) alunosAprovados.get(key_ag) / value_ag));
                    DecimalFormat df = new DecimalFormat("0.0%");
                    df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
                    String formattedAprovados = df.format(aprovados);

                    DecimalFormat df_media = new DecimalFormat("0.00");
                    df_media.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
                    String formattedMedia = df_media.format(media);

                    writer.write(formattedMedia + ";" + formattedAprovados + "\n");
                }
            }

            writer.close();

        } catch (IOException e) {
            throw new Excecao.ErroDeIO();
        }
    }
}
