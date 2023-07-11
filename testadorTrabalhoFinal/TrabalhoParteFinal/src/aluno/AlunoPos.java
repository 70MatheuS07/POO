package src.aluno;

import java.util.Map;

import src.avaliacao.Avaliacao;
import src.avaliacao.AvaliacaoMap;
import src.curso.CursoMap;

public class AlunoPos extends Aluno {
    public static final int MESTRADO = 0;
    public static final int DOUTORADO = 1;
    private int nivel;

    public AlunoPos(String nome, int nivel) {
        super(nome);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
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

        AlunoPos aluno_pos = (AlunoPos) aluno;

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
