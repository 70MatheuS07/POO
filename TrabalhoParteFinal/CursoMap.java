import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CursoMap implements Serializable{
    private Map<Integer, Curso> cursos = new HashMap<Integer, Curso>();

    public Map<Integer, Curso> getCursoMap() {
        return cursos;
    }

    /**
     * Cadastra o curso passado pelo terminal.
     * 
     * @param scanner
     */
    public void CadastrarCursos(String arquivo) throws Excecao {
        File cursoFile = new File(arquivo);

        try{
            Scanner scanner = new Scanner(cursoFile);

            // Primeira linha é o cabeçalho.
            String linha = Leitura.LehLine(scanner);

            while (scanner.hasNextLine()) {
                linha = Leitura.LehLine(scanner);
                String[] dados = linha.split(";");
                int codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];

                Curso curso = new Curso();

                curso.setCurso(nome);
                cursos.put(codigo, curso);

                System.out.printf("%d %s\n",codigo, nome);
            }
        }

        catch (FileNotFoundException e) {
            throw new Excecao("Arquivo não encontrado");
        }
    
    }
}
