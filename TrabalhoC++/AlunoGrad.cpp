#include "AlunoGrad.hpp"

AlunoGrad::AlunoGrad(const std::string &nome, int curso)
    : Aluno(nome), curso(curso) {}

int AlunoGrad::getCurso() const
{
    return curso;
}

void AlunoGrad::WriteAlunoGrad(Aluno aluno, CursoMap cursos, std::map<std::string, int> &alunosGeral,
                               std::map<std::string, double> &mediaAlunos,
                               std::map<std::string, int> &alunosAprovados,
                               AvaliacaoMap avaliacoes, std::string key_d) override
{

    double total_notas = 0.0;
    double qtd_notas = 0.0;
    double total_final = 0.0;
    double prova_final = 0.0;

    AlunoGrad aluno_grad = (AlunoGrad)aluno;

    Curso curso = cursos.getCursoMap().get(aluno_grad.getCurso());

    if (!alunosGeral.count(curso.getNome()))
    {
        alunosGeral[curso.getNome()] = 1;
        mediaAlunos[curso.getNome()] = 0.0;
        alunosAprovados[curso.getNome()] = 0;
    }
    else
    {
        int currentValue = alunosGeral[curso.getNome()];
        alunosGeral[curso.getNome()] = currentValue + 1;
    }

    // Preciso saber se ele foi aprovado.
    for (const auto &np : aluno_grad.notasProvas)
    {
        std::string key_np = np.first;
        double value_np = np.second;

        Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().get(key_np);

        if (avaliacao.getDisciplinaKey() == key_d)
        {
            Avaliacao.Valores_WriteValueAvaliacaoAluno valores;
            valores = avaliacao.CalculaMediasAluno(avaliacao, value_np);

            if (valores.getProva_final() == -1.0)
            {
                total_notas += valores.getTotal();
                qtd_notas += valores.getQtd();
            }
            else
            {
                total_notas += valores.getTotal();
                qtd_notas += valores.getQtd();
                prova_final = valores.getProva_final();
            }
        }
    }

    total_final = total_notas / qtd_notas;

    if (total_final >= 7.0)
    {
        int currentValueAprovados = alunosAprovados[curso.getNome()];
        alunosAprovados[curso.getNome()] = currentValueAprovados + 1;
    }
    else
    {
        total_final = (total_final + prova_final) / 2;

        if (total_final >= 5.0)
        {
            int currentValueAprovados = alunosAprovados[curso.getNome()];
            alunosAprovados[curso.getNome()] = currentValueAprovados + 1;
        }
    }

    double currentValueDouble = mediaAlunos[curso.getNome()];
    mediaAlunos[curso.getNome()] = currentValueDouble + total_final;
}