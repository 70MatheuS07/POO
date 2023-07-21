#include "Prova.hpp"

Prova::Prova(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int tipoProva)
    : Avaliacao(disciplina, nome, peso, data), tipoProva(tipoProva) {}

int Prova::getTipoProva() const
{
    return tipoProva;
}

/*
void Prova::ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                               Avaliacao *avaliacao, std::string key_a, double value_NA)
{
    if (dynamic_cast<Prova *>(avaliacao) && dynamic_cast<Prova *>(avaliacao)->getTipoProva() == 0)
    {
        if (totalNotas.find(key_a) == totalNotas.end())
        {
            qtdNotas[key_a] = 1;
            totalNotas[key_a] = value_NA;
        }
        else
        {
            qtdNotas[key_a]++;
            totalNotas[key_a] += value_NA;
        }
    }
}

void Prova::WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno)
{
    try
    {
        if (dynamic_cast<Prova *>(avaliacao) && dynamic_cast<Prova *>(avaliacao)->getTipoProva() == 0)
        {
            writer << key_avaliacao_aluno << ";";
        }
    }
    catch (const std::exception &e)
    {
        throw ErroDeIO();
    }
}

Avaliacao::Valores_WriteValueAvaliacaoAluno Prova::WriteValueAvaliacaoAluno(std::ofstream &writer, Avaliacao *avaliacao,
                                                                            double value_avaliacao_aluno)
{
    std::stringstream formattedTotal;
    double provaFinal = -1.0;
    Avaliacao::Valores_WriteValueAvaliacaoAluno v(0.0, 0.0, -1.0);

    try
    {
        if (dynamic_cast<Prova *>(avaliacao) && dynamic_cast<Prova *>(avaliacao)->getTipoProva() == 0)
        {
            // Formatar o valor para o formato "0.00"
            formattedTotal << std::fixed << std::setprecision(2) << value_avaliacao_aluno;
            writer << formattedTotal.str() << ";";

            double total = value_avaliacao_aluno * avaliacao->getPeso();
            double qtd_prov_trab = avaliacao->getPeso();

            v = Avaliacao::Valores_WriteValueAvaliacaoAluno(total, qtd_prov_trab, -1.0);
        }
        else
        {
            provaFinal = value_avaliacao_aluno;
            v = Avaliacao::Valores_WriteValueAvaliacaoAluno(0.0, 0.0, provaFinal);
        }

        return v;
    }
    catch (const std::exception &e)
    {
        throw ErroDeIO();
    }
}

Avaliacao::Valores_WriteValueAvaliacaoAluno Prova::CalculaMediasAluno(Avaliacao *avaliacao, double value_np)
{
    Avaliacao::Valores_WriteValueAvaliacaoAluno v(0.0, 0.0, -1.0);
    double prova_final = -1.0;

    if (dynamic_cast<Prova *>(avaliacao) && dynamic_cast<Prova *>(avaliacao)->getTipoProva() == 0)
    {
        double total_notas = value_np * avaliacao->getPeso();
        double qtd_notas = avaliacao->getPeso();

        v = Avaliacao::Valores_WriteValueAvaliacaoAluno(total_notas, qtd_notas, -1.0);
    }
    else
    {
        prova_final = value_np;
        v = Avaliacao::Valores_WriteValueAvaliacaoAluno(0.0, 0.0, prova_final);
    }

    return v;
}

*/
/*
void Prova::TratamentoExcecoes(std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                   const std::string &codigo, Avaliacao *avaliacao, double nota)
{
    long matriculaLong = std::stol(trim(dados[1]));

    // Tratamento de uma matricula fora do escopo inteiro.
    if (matriculaLong > INT_MAX)
    {
        throw Excecao("MatriculaIndefinidaLongException: Codigo: " + codigo + ", Matricula: " + std::to_string(matriculaLong));
    }

    int matricula = std::stoi(trim(dados[1]));

    // Tratamento de uma matricula fora do escopo inteiro.
    if (alunos.alunos.find(matricula) != alunos.alunos.end())
    {
        throw Excecao("MatriculaIndefinidaException: Codigo: " + codigo + ", Matricula: " + std::to_string(matricula));
    }

    if (mapaAlunos.alunos.find(matricula) != mapaAlunos.alunos.end())
    {
        throw Excecao("AlunoNaoMatriculadoException: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo +
                      ", DisciplinaKey: " + avaliacao->getDisciplinaKey());
    }

    if (alunos.alunos.find(matricula)->second.getNotasAvaliacoes().find(codigo) != alunos.alunos.find(matricula)->second.getNotasAvaliacoes().end())
    {
        {
            throw Excecao("NotaDuplicada: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo);
        }

        Aluno aluno = alunos.alunos.find(matricula)->second;
        aluno.getNotasAvaliacoes().insert(std::make_pair(codigo, nota));
    }
}
*/
