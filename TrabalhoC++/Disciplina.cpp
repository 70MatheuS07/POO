#include "Disciplina.hpp"

AlunoMap &Disciplina::getAlunoMap()
{
    return alunos;
}

void Disciplina::setDisciplina(const std::string &nome)
{
    this->nome = nome;
}

std::string Disciplina::getNome() const
{
    return nome;
}
