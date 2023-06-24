import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * Cadastra um aluno passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarAlunos(CursoMap cursos,DisciplinaMap disciplinas, String arquivo) throws Excecao {

        File disciplinaFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);
            Aluno aluno = null;

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);

                if(alunos.containsKey(matricula)){
                    throw new Excecao.MatriculasIguaisException(matricula);
                }

                String nome = dados[1];
                String[] disciplinasCSV = dados[2].split(", ");
                for(String disciplina:disciplinasCSV){
                    if (!(disciplinas.getDisciplinaMap().containsKey(disciplina))){
                        throw new Excecao.CodDisciplinaIndefinidoAlunoExcpetion(matricula, disciplina);
                    }
                }
                String tipo = dados[3];

                if (tipo.equals("G")) {
                    int curso = Integer.parseInt(dados[4]);
                    if (!(cursos.getCursoMap().containsKey(curso))){
                        throw new Excecao.CodCursoIndefinidoException(matricula, curso);
                    }
                    aluno = new AlunoGrad(nome, "G", curso);
                }

                else if (tipo.equals("P")) {
                    String curso = dados[4];
                    if (curso.equals("M"))
                        aluno = new AlunoPos(nome, "P", "Mestrado");

                    else if (curso.equals("D")){
                        aluno = new AlunoPos(nome, "P", "Doutorado");
                    }
                    else{
                        throw new Excecao.NemMNemDException(matricula, curso);
                    }
                }
                else{
                    throw new Excecao.NemGNemPException(matricula, tipo);
                }

                System.out.printf("%d %s ", matricula, nome);

                // Aparentemente coloca alunos nas disciplinas, porém na hora de imprimir o Map de alunos dentro da disciplina dá erro.
                for (String p : disciplinasCSV) {
                    Disciplina d = disciplinas.getDisciplinaMap().get(p);

                    d.getAlunoMap().getAlunoMap().put(matricula, aluno);

                    System.out.printf("%s ", p);
                }

                System.out.printf("%s\n", tipo);

                alunos.put(matricula, aluno);
            }
        }

        catch (FileNotFoundException e) {

            throw new Excecao("Arquivo não encontrado");

        }

    }

    /**
     * Registra nota do aluno no prova específica.
     * 
     * @param scanner
     */
    public void RegistraNotaAlunoAvaliacao(AvaliacaoMap avaliacoes, DisciplinaMap disciplinas, String arquivo)
            throws Excecao {

        File disciplinaFile = new File(arquivo);

        try {
            Scanner scanner = new Scanner(disciplinaFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);
            Aluno aluno = null;

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                String codigo = dados[0];

                // Pega codigo da avaliacao a partir do mapa de avaliacoes
                Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(codigo);
                // Pega o mapa de alunos da disciplina em que a avaliacao ocorreu
                Disciplina disciplina = disciplinas.getDisciplinaMap().get(avaliacao.getDisciplinaKey());
                AlunoMap mapaAlunos = disciplina.getAlunoMap();

                int matricula = -1;

                String doubleString = dados[2];
                doubleString = doubleString.replace(',', '.');
                double nota = Double.parseDouble(doubleString);

                if (avaliacao instanceof Prova) {
                    matricula = Integer.parseInt(dados[1]);

                    System.out.printf("%d\n", matricula);

                    if (mapaAlunos.alunos.containsKey(matricula) == false) {
                        System.out.println(
                                "Voce colocou um aluno que nao esta matriculado na disciplina ou que nao existe");
                        return;
                    }
                    aluno = alunos.get(matricula);
                    System.out.print("Digite a nota da prova: ");
                    aluno.getNotasAvaliacoes().put(codigo, nota);

                } else {
                    ArrayList<Integer> matriculas = new ArrayList<Integer>();
                    int qtd = 0;
                    // loop para ler as matriculas dos alunos, armazenando em uma lista
                    String[] matriculasString = dados[1].split(", ");
                    for (String s : matriculasString) {
                        matricula = Integer.parseInt(s);

                        // confere se o aluno esta na no mapa de alunos da disciplina
                        if (mapaAlunos.alunos.containsKey(matricula) == false) {
                            System.out
                                    .println(
                                            "Voce colocou um aluno que nao esta matriculado na disciplina ou que nao existe");
                            System.out.println("Tente novamente");
                            continue;
                        }
                        qtd++;
                        matriculas.add(matricula);
                    }
                    int i = 0;
                    while (i < qtd) {
                        aluno = alunos.get(matriculas.get(i));
                        aluno.getNotasAvaliacoes().put(codigo, nota);
                        i++;
                    }
                }

            }
        }

        catch (FileNotFoundException e) {

            throw new Excecao("Arquivo não encontrado");

        }

    }

}
