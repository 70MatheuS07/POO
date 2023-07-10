package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Relatorio {

    /**
     * Escolhe o relatório específico.
     * 
     * @param scanner
     * @param d
     * @param a
     */
    public void EscolheRelatorio(Scanner scanner, DisciplinaMap d, AvaliacaoMap a) {
        int num = 0;
        boolean valido = false;

        RelatorioMenu();

        while (!valido) {
            try {
                System.out.print("Digite um número inteiro: ");
                num = Leitura.LehInt(scanner);
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Você digitou um valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }

        switch (num) {
            case 0:
                break;

            case 1:
                PautaFinalDisciplina(scanner, d, a);
                break;

            case 2:
                EstatisticasDisciplina(d, a);
                break;

            case 3:
                EstatisticasAvaliacao(a, d);
                break;
        }

    }

    /**
     * Escolhe a pauta da disciplina.
     * 
     * @param scanner
     * @param d
     * @param a
     */
    public void PautaFinalDisciplina(Scanner scanner, DisciplinaMap d, AvaliacaoMap a) {
        System.out.print("Qual disciplina você quer a pauta: ");
        String id = Leitura.LehLine(scanner);
        Disciplina disciplina = d.getDisciplinaMap().get(id);

        Map<Integer, Aluno> alunos = disciplina.getAlunoMap().getAlunoMap();

        for (Map.Entry<Integer, Aluno> entry : alunos.entrySet()) {
            int matricula = entry.getKey();
            Aluno aluno = entry.getValue();
            String nomeAluno = aluno.getNome();

            ImprimeNotasProvasAluno(id, aluno.notasProvas, a.getAvaliacaoMap(), matricula, nomeAluno);
        }
    }

    /**
     * Imprime notas das provas do aluno.
     * 
     * @param id
     * @param notas
     * @param avaliacoes
     * @param matricula
     * @param nomeAluno
     */
    public void ImprimeNotasProvasAluno(String id, Map<String, Double> notas, Map<String, Avaliacao> avaliacoes,
            int matricula, String nomeAluno) {
        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            String keyProva = entry.getKey();
            double notaProva = entry.getValue();
            String nomeProva = avaliacoes.get(keyProva).getNome();

            Avaliacao avaliacao = avaliacoes.get(keyProva);

            if (id.equals(avaliacao.getDisciplinaKey())) {
                System.out.printf("%d %s\n", matricula, nomeAluno);

                System.out.printf("- %s nota: %.2f; ", nomeProva, notaProva);

                double mediaParcial = CalculaMediaParcial(notas, avaliacoes);

                if (mediaParcial >= 7.0) {
                    System.out.printf("média final: %.2f\n", mediaParcial);
                } else {
                    System.out.printf("média parcial: %.2f; ", mediaParcial);

                    double notaProvaFinal = RetornaNotaProvaFinal(notas, avaliacoes);

                    System.out.printf("nota prova final: %.2f; ", notaProvaFinal);

                    double mediaFinal = (mediaParcial + notaProvaFinal) / 2;

                    System.out.printf("média final: %.2f\n", mediaFinal);
                }
            }
        }
    }

    /**
     * Calcula média parcial.
     * 
     * @param notas
     * @param avaliacoes
     * @return
     */
    public double CalculaMediaParcial(Map<String, Double> notas, Map<String, Avaliacao> avaliacoes) {
        double total = 0.0, pesoTotal = 0.0;

        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            String keyProva = entry.getKey();
            double notaProva = entry.getValue();
            Avaliacao avaliacao = avaliacoes.get(keyProva);
            double pesoProva = avaliacao.getPeso();

            if (avaliacao instanceof Prova) {
                Prova prova = (Prova) avaliacao;

                if (prova.getTipoProva() == 0) {
                    total += notaProva * pesoProva;
                    pesoTotal += pesoProva;
                }
            } else {
                total += notaProva * pesoProva;
                pesoTotal += pesoProva;
            }
        }

        return total / pesoTotal;
    }

    /**
     * Devolve nota da prova final.
     * 
     * @param notas
     * @param avaliacoes
     * @return
     */
    public double RetornaNotaProvaFinal(Map<String, Double> notas, Map<String, Avaliacao> avaliacoes) {
        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            String keyProva = entry.getKey();
            double notaProva = entry.getValue();
            Avaliacao avaliacao = avaliacoes.get(keyProva);

            if (avaliacao instanceof Prova) {
                Prova prova = (Prova) avaliacao;

                if (prova.getTipoProva() == 1) {
                    return notaProva;
                }
            }
        }

        return -1;
    }

    /**
     * Estatísticas por disciplina.
     * 
     * @param d
     * @param a
     */
    public void EstatisticasDisciplina(DisciplinaMap d, AvaliacaoMap a) {
        Map<String, Disciplina> disciplinas = d.getDisciplinaMap();

        System.out.println("- Estatísticas por disciplina:");

        for (Map.Entry<String, Disciplina> entry : disciplinas.entrySet()) {
            String key = entry.getKey();
            Disciplina disciplina = entry.getValue();

            System.out.printf("\t- Código: %s; Nome: %s; ", key, disciplina.getNome());

            /*
             * Calcular e exibir a média das notas finais dos alunos
             * daquele curso e o percentual de alunos aprovados daquele curso.
             */

            CalculaMediaAlunosDisciplina(disciplina, a);
        }
    }

    /**
     * Calcula a média de alunos da disciplina.
     * 
     * @param disciplina
     * @param a
     */
    public void CalculaMediaAlunosDisciplina(Disciplina disciplina, AvaliacaoMap a) {
        Map<Integer, Aluno> alunos = disciplina.getAlunoMap().getAlunoMap();

        int num = 0;

        for (Map.Entry<Integer, Aluno> entry : alunos.entrySet()) {
            Aluno aluno = entry.getValue();

            num += DifereAlunos(aluno, a);

        }

        double taxaAprovacao = ((double) num / alunos.size()) * 100;

        System.out.printf("percentual de alunos aprovados: %.2f%%\n", taxaAprovacao);
    }

    /**
     * Analiza e diferencia os alunos.
     * 
     * @param aluno
     * @param a
     * @return
     */
    public int DifereAlunos(Aluno aluno, AvaliacaoMap a) {
        double mediaFinal = 0;
        if (aluno instanceof AlunoGrad) {
            AlunoGrad alunoGrad = (AlunoGrad) aluno;

            int curso = alunoGrad.getCurso();

            Map<String, Double> notas = alunoGrad.getNotasAvaliacoes();

            System.out.printf("Curso: %d; ", curso);

            double mediaParcial = CalculaMediaParcial(notas, a.getAvaliacaoMap());

            if (mediaParcial >= 7.0) {
                System.out.printf("média notas final: %.2f; ", mediaParcial);
            } else {
                double notaProvaFinal = RetornaNotaProvaFinal(notas, a.getAvaliacaoMap());

                mediaFinal = (mediaParcial + notaProvaFinal) / 2;

                System.out.printf("média notas final: %.2f; ", mediaFinal);
            }
        }

        else {
            AlunoPos alunoPos = (AlunoPos) aluno;

            String curso = null;

            if (alunoPos.getNivel() == AlunoPos.MESTRADO) {

                if (alunoPos.getNivel() == AlunoPos.MESTRADO) {
                    curso = "Mestrado";
                } else {
                    curso = "Doutorado";
                }

                Map<String, Double> notas = alunoPos.getNotasAvaliacoes();

                System.out.printf("Curso: %s; ", curso);

                double mediaParcial = CalculaMediaParcial(notas, a.getAvaliacaoMap());

                if (mediaParcial >= 7.0) {
                    System.out.printf("média notas final: %.2f; ", mediaParcial);
                } else {
                    double notaProvaFinal = RetornaNotaProvaFinal(notas, a.getAvaliacaoMap());

                    mediaFinal = (mediaParcial + notaProvaFinal) / 2;

                    System.out.printf("média notas final: %.2f; ", mediaFinal);
                }
            } else {

                if (alunoPos.getNivel() == AlunoPos.MESTRADO) {
                    curso = "Mestrado";
                } else {
                    curso = "Doutorado";
                }

                Map<String, Double> notas = alunoPos.getNotasAvaliacoes();

                System.out.printf("Curso: %s; ", curso);

                double mediaParcial = CalculaMediaParcial(notas, a.getAvaliacaoMap());

                if (mediaParcial >= 7.0) {
                    System.out.printf("média notas final: %.2f; ", mediaParcial);
                    mediaFinal = mediaParcial;
                } else {
                    double notaProvaFinal = RetornaNotaProvaFinal(notas, a.getAvaliacaoMap());

                    mediaFinal = (mediaParcial + notaProvaFinal) / 2;

                    System.out.printf("média notas final: %.2f; ", mediaFinal);
                }
            }
        }

        if (mediaFinal >= 7.0) {
            return 1;
        }

        return 0;
    }

    /**
     * Calcula a média das notas obtidas.
     * 
     * @param a
     * @param d
     */
    public void EstatisticasAvaliacao(AvaliacaoMap a, DisciplinaMap d) {
        Map<String, Avaliacao> avaliacoes = a.getAvaliacaoMap();

        for (Map.Entry<String, Avaliacao> entry : avaliacoes.entrySet()) {
            String key = entry.getKey();
            Avaliacao avaliacao = entry.getValue();
            String avaliacaoNome = avaliacao.getNome();
            Date data = avaliacao.getData();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String avaliacaoData = formato.format(data);

            String disciplinaKey = avaliacao.getDisciplinaKey();
            System.out.printf("Código: %s; Código disciplina: %s; ", key, disciplinaKey);
            System.out.printf("Nome: %s; Data: %s; ", avaliacaoNome, avaliacaoData);

            // Fazer a média das notas obtidas.
            Disciplina disciplina = d.getDisciplinaMap().get(disciplinaKey);

            double mediaNotasObtidas = CalculaMediaAlunosDisciplinaAvaliacao(disciplina, key);
            System.out.printf("média notas obtidas: %.2f\n", mediaNotasObtidas);
        }
    }

    /**
     * Calcula a media dos alunos nas avaliações.
     * 
     * @param disciplina
     * @param key
     * @return
     */
    public double CalculaMediaAlunosDisciplinaAvaliacao(Disciplina disciplina, String key) {
        Map<Integer, Aluno> alunos = disciplina.getAlunoMap().getAlunoMap();

        double total = 0.0;

        for (Map.Entry<Integer, Aluno> entry : alunos.entrySet()) {
            Aluno aluno = entry.getValue();

            if (aluno.getNotasAvaliacoes().containsKey(key)) {
                Double notaAvaliacao = aluno.getNotasAvaliacoes().get(key);
                total += notaAvaliacao;
            } else {
                // Tratar a ausência da chave (por exemplo, atribuir zero)
                total += 0.0;
            }
        }

        return ((double) total / alunos.size());
    }

    /**
     * Imprime um menu com as opções de relatório.
     */
    public void RelatorioMenu() {
        System.out.println("Opcoes de relatório:");
        System.out.println("1 - Pauta final de disciplina.");
        System.out.println("2 - Estatísticas por disciplina.");
        System.out.println("3 - Estatísticas por avaliação.");
        System.out.println("0 - Sair do relatório.");
    }

}
