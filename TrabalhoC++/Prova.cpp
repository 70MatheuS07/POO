#include "Prova.hpp"

Prova::Prova(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int tipoProva)
    : Avaliacao(disciplina, nome, peso, data), tipoProva(tipoProva) {}

int Prova::getTipoProva() const
{
    return tipoProva;
}

void Prova::ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                               Avaliacao *avaliacao, std::string key_a, double value_NA)
{
    // Implementação da função ModificaMapasNotas
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
        throw Excecao::ErroDeIO();
    }
}

