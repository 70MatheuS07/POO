#include "AlunoGrad.hpp"

AlunoGrad::AlunoGrad(const std::string &nome, int curso)
    : Aluno(nome), curso(curso) {}

int AlunoGrad::getCurso() const
{
    return curso;
}

/*

void AlunoGrad::WriteAlunoGrad(Aluno *aluno, CursoMap &cursos,
                               std::map<std::string, int> &alunosGeral,
                               std::map<std::string, double> &mediaAlunos,
                               std::map<std::string, int> &alunosAprovados,
                               AvaliacaoMap &avaliacoes, std::string key_d)
{

    double total_notas = 0.0;
    double qtd_notas = 0.0;
    double total_final = 0.0;
    double prova_final = 0.0;

    AlunoGrad *aluno_grad = (AlunoGrad *)aluno;

    Curso *curso = nullptr;

    // Encontrar o iterador para o par no mapa associado ao curso
    auto a = cursos.getCursoMap().find(aluno_grad->getCurso());

    // Acessar o ponteiro para o objeto Curso a partir do iterador
    curso = &(a->second);

    if (!alunosGeral.count(curso->getNome()))
    {
        alunosGeral[curso->getNome()] = 1;
        mediaAlunos[curso->getNome()] = 0.0;
        alunosAprovados[curso->getNome()] = 0;
    }
    else
    {
        int currentValue = alunosGeral[curso->getNome()];
        alunosGeral[curso->getNome()] = currentValue + 1;
    }

    // Preciso saber se ele foi aprovado.
    for (const auto &np : aluno_grad->notasProvas)
    {
        std::string key_np = np.first;
        double value_np = np.second;

        auto a = avaliacoes.getAvaliacaoMap().find(key_np);

        Prova *prova = dynamic_cast<Prova *>(&a->second);

        if (prova)
        {
            Avaliacao::Valores_WriteValueAvaliacaoAluno valores = prova->CalculaMediasAluno(prova, value_np);

            if (prova->getDisciplinaKey() == key_d)
            {
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
        else
        {
            Trabalho *trabalho = dynamic_cast<Trabalho *>(&a->second);

            if (trabalho)
            {
                Avaliacao::Valores_WriteValueAvaliacaoAluno valores = trabalho->CalculaMediasAluno(trabalho, value_np);

                if (trabalho->getDisciplinaKey() == key_d)
                {
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
        }
    }

    total_final = total_notas / qtd_notas;

    if (total_final >= 7.0)
    {
        int currentValueAprovados = alunosAprovados[curso->getNome()];
        alunosAprovados[curso->getNome()] = currentValueAprovados + 1;
    }
    else
    {
        total_final = (total_final + prova_final) / 2;

        if (total_final >= 5.0)
        {
            int currentValueAprovados = alunosAprovados[curso->getNome()];
            alunosAprovados[curso->getNome()] = currentValueAprovados + 1;
        }
    }

    double currentValueDouble = mediaAlunos[curso->getNome()];
    mediaAlunos[curso->getNome()] = currentValueDouble + total_final;
}

*/