#include "Dados.hpp"

// Implementação dos métodos de Dados

AlunoMap *Dados::getAlunos() const
{
    return alunos;
}

void Dados::setAlunos(AlunoMap *alunos)
{
    this->alunos = alunos;
}

CursoMap *Dados::getCursos() const
{
    return cursos;
}

void Dados::setCursos(CursoMap *cursos)
{
    this->cursos = cursos;
}

DisciplinaMap *Dados::getDisciplinas() const
{
    return disciplinas;
}

void Dados::setDisciplinas(DisciplinaMap *disciplinas)
{
    this->disciplinas = disciplinas;
}

AvaliacaoMap *Dados::getAvaliacoes() const
{
    return provas;
}

void Dados::setAvaliacoes(AvaliacaoMap *provas)
{
    this->provas = provas;
}
