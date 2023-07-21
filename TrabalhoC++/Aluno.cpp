#include "Aluno.hpp"

Aluno::Aluno(const std::string &nome) : nome(nome) {}

std::string Aluno::getNome() const
{
    return nome;
}

std::map<std::string, double> Aluno::getNotasAvaliacoes() const
{
    return notasProvas;
}