#include "Avaliacao.hpp"

Avaliacao::Avaliacao(const std::string &disciplina, const std::string &nome, double peso, std::time_t data)
    : disciplinaKey(disciplina), nome(nome), peso(peso), data(data) {}

std::string Avaliacao::getDisciplinaKey() const
{
    return disciplinaKey;
}

std::string Avaliacao::getNome() const
{
    return nome;
}

double Avaliacao::getPeso() const
{
    return peso;
}

std::time_t Avaliacao::getData() const
{
    return data;
}

void Avaliacao::ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                                   Avaliacao *avaliacao, std::string key_a, double value_NA) {}

void Avaliacao::WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) {}

Avaliacao::Valores_WriteValueAvaliacaoAluno::Valores_WriteValueAvaliacaoAluno(double total, double qtd,
                                                                              double prova_final)
    : total(total), qtd(qtd), prova_final(prova_final) {}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getTotal() const
{
    return total;
}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getQtd() const
{
    return qtd;
}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getProva_final() const
{
    return prova_final;
}

Avaliacao::Valores_WriteValueAvaliacaoAluno::Valores_WriteValueAvaliacaoAluno(double total, double qtd,
                                                                              double prova_final)
    : total(total), qtd(qtd), prova_final(prova_final) {}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getTotal() const
{
    return total;
}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getQtd() const
{
    return qtd;
}

double Avaliacao::Valores_WriteValueAvaliacaoAluno::getProva_final() const
{
    return prova_final;
}