package src.aluno;

import java.util.Map;

import src.avaliacao.Avaliacao;
import src.avaliacao.AvaliacaoMap;
import src.curso.Curso;
import src.curso.CursoMap;

public class AlunoGrad extends Aluno {
    private int curso;

    public AlunoGrad(String nome, int curso) {
        super(nome);
        this.curso = curso;
    }

    public int getCurso() {
        return curso;
    }

    @Override
    public void WriteAlunoGrad(Aluno aluno, CursoMap cursos, Map<String, Integer> alunosGeral,
            Map<String, Double> mediaAlunos,
            Map<String, Integer> alunosAprovados,
            AvaliacaoMap avaliacoes, String key_d) {

        double total_notas = 0.0;
        double qtd_notas = 0.0;
        double total_final = 0.0;
        double prova_final = 0.0;

        AlunoGrad aluno_grad = (AlunoGrad) aluno;

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
                Avaliacao.Valores_WriteValueAvaliacaoAluno valores = null;
                valores = avaliacao.CalculaMediasAluno(avaliacao, value_np);

                if (valores.getProva_final() == -1.0) {
                    total_notas += valores.getTotal();
                    qtd_notas += valores.getQtd();
                } else {
                    total_notas += valores.getTotal();
                    qtd_notas += valores.getQtd();
                    prova_final = valores.getProva_final();
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
    }
}