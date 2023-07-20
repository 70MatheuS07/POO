#include "Trabalho.hpp"

Trabalho::Trabalho(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int qtdGrupos)
    : Avaliacao(disciplina, nome, peso, data), qtdGrupos(qtdGrupos) {}

int Trabalho::getQtdGrupos() const
{
    return qtdGrupos;
}

void Trabalho::ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                                  Avaliacao *avaliacao, std::string key_a, double value_NA)
{
    // Implementação da função ModificaMapasNotas
    if (dynamic_cast<Trabalho *>(avaliacao) && dynamic_cast<Trabalho *>(avaliacao)->getTipoProva() == 0)
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

void Trabalho::WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno)
{
    try
    {
        writer << key_avaliacao_aluno << ";";
    }
    catch (const std::exception &e)
    {
        throw ErroDeIO();
    }
}

Valores_WriteValueAvaliacaoAluno Trabalho::WriteValueAvaliacaoAluno(std::ofstream &writer, double value_avaliacao_aluno)
{
    try
    {
        std::locale localeGerman("German");
        writer.imbue(localeGerman);

        std::string formattedTotal = std::to_string(value_avaliacao_aluno);
        writer << formattedTotal << ";";

        double total = value_avaliacao_aluno * getPeso();
        double qtd_prov_trab = getPeso();

        return Valores_WriteValueAvaliacaoAluno(total, qtd_prov_trab, -1.0);
    }
    catch (std::ofstream::failure &e)
    {
        throw Excecao("ErroDeIO");
    }
}

Valores_WriteValueAvaliacaoAluno Trabalho::CalculaMediasAluno(double value_np) const
{
    double total_notas = value_np * getPeso();
    double qtd_notas = getPeso();

    return Valores_WriteValueAvaliacaoAluno(total_notas, qtd_notas, -1.0);
}