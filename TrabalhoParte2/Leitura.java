import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Leitura {

    File cursosFile = new File("cursos.csv");
    File disciplinasFile = new File("disciplinas.csv");
    File alunosFile = new File("alunos.csv");
    File avaliacoesFile = new File("avaliacoes.csv");
    File notasFile = new File("notas.csv");

    public CursoMap LehCursos() {
        CursoMap aux = new CursoMap();

        try {
            Scanner scanner = new Scanner(cursosFile);
            scanner.nextLine(); // Ignora a primeira linha, que contém o cabeçalho do arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                int codigo = Integer.parseInt(campos[0]);
                String nome = campos[1];

                Curso curso = new Curso();
                curso.setCurso(nome);
                aux.getCursoMap().put(codigo, curso);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado\n");
        }

        return aux;
    }

    public DisciplinaMap LehDisciplinas() {
        DisciplinaMap aux = new DisciplinaMap();

        try {
            Scanner scanner = new Scanner(disciplinasFile);
            scanner.nextLine(); // Ignora a primeira linha, que contém o cabeçalho do arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String codigo = campos[0];
                String nome = campos[1];

                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina(nome);
                aux.getDisciplinaMap().put(codigo, disciplina);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado\n");
        }

        return aux;
    }

    public AlunoMap LehAlunos(Map<String, Disciplina> disciplinas) {
        AlunoMap aux = new AlunoMap();

        try {
            Scanner scanner = new Scanner(alunosFile);
            scanner.nextLine(); // Ignora a primeira linha, que contém o cabeçalho do arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                int matricula = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String disciplina = campos[2];
                String tipo = campos[3];
                Object curso = campos[4];

                Aluno aluno = new Aluno();
                aluno.setAluno(nome, tipo, curso);

                String[] disciplinasMatriz = disciplina.split(", ");
                for (String str : disciplinasMatriz) {
                    disciplinas.get(str).getAlunoMap().CadastrarAluno(matricula, aluno);
                }

                aux.getAlunoMap().put(matricula, aluno);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado\n");
        }

        return aux;
    }

    public void LehAvaliacoes() {
        try {
            Scanner scanner = new Scanner(disciplinasFile);
            scanner.nextLine(); // Ignora a primeira linha, que contém o cabeçalho do arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String disciplina = campos[0];
                String codigo = campos[1];
                String nome = campos[2];
                Double peso = Double.parseDouble(campos[3]);
                String tipo = campos[4];

                DateFormat df = DateFormat.getDateInstance();
                Date data = df.parse(campos[5]);

                if (tipo == "T") {
                    int tamGrupo = Integer.parseInt(campos[6]);
                }

                
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado\n");
        }

    }

    public ProvaMap LehProvas() {
        ProvaMap aux = new ProvaMap();

        try {
            Scanner scanner = new Scanner(disciplinasFile);
            scanner.nextLine(); // Ignora a primeira linha, que contém o cabeçalho do arquivo
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String codigo = campos[0];
                String nome = campos[1];

                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina(nome);
                aux.getDisciplinaMap().put(codigo, disciplina);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado\n");
        }

        return aux;
    }
}
