#include "Aluno.hpp"

Aluno::Aluno(const std::string &nome, const std::string &grad)
    : nome(nome), grad(grad) {}

std::string Aluno::getNome() const
{
    return nome;
}

std::unordered_map<std::string, double> Aluno::getNotasAvaliacoes() const
{
    return notasProvas;
}
