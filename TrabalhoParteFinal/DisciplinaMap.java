import java.io.File;
import java.io.FileNotFoundException;
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
                String codigo = dados[0];
                String nome = dados[1];

                Disciplina disciplina = new Disciplina();

                disciplina.setDisciplina(nome);
                disciplinas.put(codigo, disciplina);

                System.out.printf("%s %s\n", codigo, nome);
            }
        }

        catch (FileNotFoundException e) {

            throw new Excecao("Arquivo não encontrado");

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

    public void CriaPautaDisciplinas(AlunoMap alunos, AvaliacaoMap avaliacoes) {
        try {
            double total = 0.0;
            int qtd_prov_trab = 0;

            for (Map.Entry<String, Disciplina> d : disciplinas.entrySet()) {
                String key_d = d.getKey();
                Disciplina value_d = d.getValue();

                FileWriter writer = new FileWriter("1-pauta-" + key_d + ".csv");

                writer.write("Matrícula;Aluno;");

                Map<Integer, Aluno> aluno_map = value_d.getAlunoMap().getAlunoMap();

                for (Map.Entry<Integer, Aluno> entry_aluno : aluno_map.entrySet()) {
                    int key_a = entry_aluno.getKey();
                    Aluno value_a = entry_aluno.getValue();

                    Map<String, Double> avaliacoes_aluno = value_a.getNotasAvaliacoes();

                    for (Map.Entry<String, Double> entry_ava : avaliacoes_aluno.entrySet()) {
                        String key_avaliacao_aluno = entry_ava.getKey();
                        double value_avaliacao_aluno = entry_ava.getValue();

                        Avaliacao aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                        if (aa instanceof Prova) {
                            if (!((Prova) aa).getTipoProva()) {
                                writer.write(key_avaliacao_aluno + ";");
                            }

                        } else {
                            writer.write(key_avaliacao_aluno + ";");
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

                    for (Map.Entry<String, Double> avaliacao_aluno : avaliacoesAluno.entrySet()) {
                        String key_avaliacao_aluno = avaliacao_aluno.getKey();
                        double value_avaliacao_aluno = avaliacao_aluno.getValue();

                        System.out.println(key_avaliacao_aluno);
                        System.out.println(key_d);

                        Avaliacao aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                        if (aa instanceof Prova) {
                            if (!((Prova) aa).getTipoProva()) {
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
            e.printStackTrace();
        }
    }
}
