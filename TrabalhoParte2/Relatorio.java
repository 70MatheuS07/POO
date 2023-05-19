import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Relatorio {

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
                Disciplina disciplina = SelecionaDisciplina(scanner, d);
                PautaFinalDisciplina(disciplina, a);
                break;

            case 2:
                EstatisticasDisciplina(d);
                break;

            case 3:
                EstatisticasAvaliacao(a, d);
                break;
        }

    }

    public Disciplina SelecionaDisciplina(Scanner scanner, DisciplinaMap d) {
        System.out.print("Qual disciplina você quer a pauta: ");
        String id = Leitura.LehLine(scanner);
        Disciplina disciplina = d.getDisciplinaMap().get(id);
        return disciplina;
    }

    public void PautaFinalDisciplina(Disciplina disciplina, AvaliacaoMap a) {
        Map<Integer, Aluno> alunos = disciplina.getAlunoMap().getAlunoMap();

        for (Map.Entry<Integer, Aluno> entry : alunos.entrySet()) {
            int matricula = entry.getKey();
            Aluno aluno = entry.getValue();
            String nomeAluno = aluno.getNome();

            System.out.printf("%d %s\n", matricula, nomeAluno);

            ImprimeNotasProvasAluno(aluno.notasProvas, a.getAvaliacaoMap());
        }
    }

    public void ImprimeNotasProvasAluno(Map<String, Double> notas, Map<String, Avaliacao> avaliacoes) {
        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            String keyProva = entry.getKey();
            double notaProva = entry.getValue();
            String nomeProva = avaliacoes.get(keyProva).getNome();

            System.out.printf("- %s nota: %.2f\n", nomeProva, notaProva);

            // CalculaMediaParcial();
            // NotaProvaFinal(); Se ele fizer a final.
            // MediaFinal(); Se ele fizer a final.
        }
    }

    public void EstatisticasDisciplina(DisciplinaMap d) {
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
        }
    }

    public void EstatisticasAvaliacao(AvaliacaoMap a, DisciplinaMap d) {
        Map<String, Avaliacao> avaliacoes = a.getAvaliacaoMap();

        for (Map.Entry<String, Avaliacao> entry : avaliacoes.entrySet()) {
            String key = entry.getKey();
            Avaliacao avaliacao = entry.getValue();
            String avaliacaoNome = avaliacao.getNome();
            Date data = avaliacao.getData();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String avaliacaoData = formato.format(data);

            String disciplinaKey = avaliacao.getDisciplina();
            System.out.printf("Código: %s; Código disciplina: %s; ", key, disciplinaKey);
            System.out.printf("Nome: %s; Data: %s", key, disciplinaKey, avaliacaoNome, avaliacaoData);

            // Fazer a média das notas obtidas.
        }
    }

    public void RelatorioMenu() {
        System.out.println("Opcoes de relatório:");
        System.out.println("1 - Pauta final de disciplina.");
        System.out.println("2 - Estatísticas por disciplina.");
        System.out.println("3 - Estatísticas por avaliação.");
        System.out.println("0 - Sair do relatório.");
    }

}
