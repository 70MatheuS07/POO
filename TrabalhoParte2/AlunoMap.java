import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlunoMap implements Serializable {
    private Map<Integer, Aluno> alunos = new HashMap<Integer, Aluno>();

    public Map<Integer, Aluno> getAlunoMap() {
        return alunos;
    }

    public void setAlunoMap(Map<Integer, Aluno> alunos) {
        this.alunos = alunos;
    }

    /**
     * Cadastra um aluno passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarAluno(Scanner scanner) {
        int matricula;

        while (true) {
            System.out.print("Digite sua matricula: ");
            matricula = Leitura.LehInt(scanner);

            if (!alunos.containsKey(matricula)) {
                break;
            }

            System.out.println("\nEssa matricula ja existe, tente outra.\n");

        }

        System.out.print("Digite seu nome: ");
        String nome = Leitura.LehLine(scanner);

        System.out.println("Digite G - Graduacao\nDigite P - Pos graduacao");
        String PG = Leitura.LehLine(scanner);
        Aluno aluno;
        if (PG.equals("G")) {
            System.out.print("Digite qual curso deseja fazer: ");
            int curso = Leitura.LehInt(scanner);
            aluno = new AlunoGrad(nome, "G", curso);

        } else {
            System.out.print("Digite M - Mestrado\n D - Doutorado ");
            String MD = Leitura.LehLine(scanner);
            if (MD.equals("M"))
                aluno = new AlunoPos(nome, "P", "Mestrado");

            else {
                aluno = new AlunoPos(nome, "P", "Doutorado");
            }

        }

        alunos.put(matricula, aluno);
    }

    /**
     * Registra nota do aluno no prova específica.
     * 
     * @param scanner
     */
    public void RegistraNotaAlunoAvaliacao(AvaliacaoMap avaliacoes, Scanner scanner) {
        System.out.print("Digite o codigo da avaliacao: ");
        String codigo = Leitura.LehLine(scanner);
        //Pega codigo da avaliacao  a partir do mapa de avaliacoes
        Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(codigo);
        //Pega o mapa de alunos da disciplina em que a avaliacao ocorreu
        AlunoMap mapaAlunos = avaliacao.getDisciplina().getAlunoMap();

        Aluno aluno = null;
        int matricula = -1;
        double nota = 0;
        if (avaliacao instanceof Prova) {
            System.out.print("Digite a matricula do aluno: ");
            matricula = Leitura.LehInt(scanner);
            //confere se o aluno esta na no mapa de alunos da disciplina
            if (mapaAlunos.alunos.containsKey(matricula) == false) {
                System.out.println("Voce colocou um aluno que nao esta matriculado na disciplina ou que nao existe");
                return;
            }
            aluno = alunos.get(matricula);
            System.out.print("Digite a nota da prova: ");
            nota = Leitura.LehDouble(scanner);
            aluno.getNotasAvaliacoes().put(codigo, nota);

        } else {
            ArrayList<Integer> matriculas = new ArrayList<Integer>();
            int qtd = 0;
            System.out.print("Digite a matricula do aluno: ");
            //loop para ler as matriculas dos alunos, armazenando em uma lista
            while (true) {
                matricula = Leitura.LehInt(scanner);
                if (matricula == 0) {
                    break;
                }
                //confere se o aluno esta na no mapa de alunos da disciplina
                if (mapaAlunos.alunos.containsKey(matricula) == false) {
                    System.out.println("Voce colocou um aluno que nao esta matriculado na disciplina ou que nao existe");
                    System.out.println("Tente novamente");
                    continue;
                }
                qtd++;
                matriculas.add(matricula);
                System.out.println("Se ja forneceu todos os alunos do grupo digite 0");
                System.out.print("Se nao, digite a matricula do proximo aluno: ");
            }
            int i = 0;
            System.out.print("Digite a nota do trabalho: ");
            nota = Leitura.LehDouble(scanner);
            while (i < qtd) {
                aluno = alunos.get(matriculas.get(i));
                aluno.getNotasAvaliacoes().put(codigo, nota);
                i++;
            }
        }

    }

}
