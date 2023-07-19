#include "Trabalho.hpp"

Trabalho::Trabalho(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int qtdGrupos)
    : Avaliacao(disciplina, nome, peso, data), qtdGrupos(qtdGrupos) {}

int Trabalho::getQtdGrupos() const
{
    return qtdGrupos;
}