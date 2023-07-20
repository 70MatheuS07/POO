#include "AlunoPos.hpp"

AlunoPos::AlunoPos(const std::string &nome, int nivel) : Aluno(nome), nivel(nivel) {}

int AlunoPos::getNivel() const
{
    return nivel;
}

void AlunoPos::WriteAlunoGrad(Aluno aluno, CursoMap cursos, std::map<std::string, int> &alunosGeral,
                              std::map<std::string, double> &mediaAlunos,
                              std::map<std::string, int> &alunosAprovados,
                              AvaliacaoMap avaliacoes, std::string key_d) override
{

    double total_notas = 0.0;
    double qtd_notas = 0.0;
    double total_final = 0.0;
    double prova_final = 0.0;

    AlunoPos aluno_pos = (AlunoPos)aluno;

    if (aluno_pos.getNivel() == AlunoPos::MESTRADO)
    {
        if (!alunosGeral.count("Mestrado"))
        {
            alunosGeral["Mestrado"] = 1;
            mediaAlunos["Mestrado"] = 0.0;
            alunosAprovados["Mestrado"] = 0;
        }
        else
        {
            int currentValue = alunosGeral["Mestrado"];
            alunosGeral["Mestrado"] = currentValue + 1;
        }

        // Preciso saber se ele foi aprovado.
        for (const auto &np : aluno_pos.getNotasAvaliacoes())
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
            int currentValueAprovados = alunosAprovados["Mestrado"];
            alunosAprovados["Mestrado"] = currentValueAprovados + 1;
        }
        else
        {
            total_final = (total_final + prova_final) / 2;

            if (total_final >= 5.0)
            {
                int currentValueAprovados = alunosAprovados["Mestrado"];
                alunosAprovados["Mestrado"] = currentValueAprovados + 1;
            }
        }

        double currentValueDouble = mediaAlunos["Mestrado"];
        mediaAlunos["Mestrado"] = currentValueDouble + total_final;
    }
    else
    {
        if (!alunosGeral.count("Doutorado"))
        {
            alunosGeral["Doutorado"] = 1;
            mediaAlunos["Doutorado"] = 0.0;
            alunosAprovados["Doutorado"] = 0;
        }
        else
        {
            int currentValue = alunosGeral["Doutorado"];
            alunosGeral["Doutorado"] = currentValue + 1;
        }

        // Preciso saber se ele foi aprovado.
        for (const auto &np : aluno_pos.getNotasAvaliacoes())
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
            int currentValueAprovados = alunosAprovados["Doutorado"];
            alunosAprovados["Doutorado"] = currentValueAprovados + 1;
        }
        else
        {
            total_final = (total_final + prova_final) / 2;

            if (total_final >= 5.0)
            {
                int currentValueAprovados = alunosAprovados["Doutorado"];
                alunosAprovados["Doutorado"] = currentValueAprovados + 1;
            }
        }

        double currentValueDouble = mediaAlunos["Doutorado"];
        mediaAlunos["Doutorado"] = currentValueDouble + total_final;
    }
}