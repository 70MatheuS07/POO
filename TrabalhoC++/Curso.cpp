#include "Curso.hpp"

void Curso::setCurso(const std::string &nome)
{
    this->nome = nome;
}

std::string Curso::getNome() const
{
    return nome;
}