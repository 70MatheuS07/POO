#include "AlunoPos.hpp"

AlunoPos::AlunoPos(const std::string &nome, int nivel) : Aluno(nome), nivel(nivel) {}

int AlunoPos::getNivel() const
{
    return nivel;
}
