#include "Dados.hpp"

AlunoMap &Dados::getAlunos()
{
    return alunos;
}

void Dados::setAlunos(const AlunoMap &alunos)
{
    this->alunos = alunos;
}

CursoMap &Dados::getCursos()
{
    return cursos;
}

void Dados::setCursos(const CursoMap &cursos)
{
    this->cursos = cursos;
}

DisciplinaMap &Dados::getDisciplinas()
{
    return disciplinas;
}

void Dados::setDisciplinas(const DisciplinaMap &disciplinas)
{
    this->disciplinas = disciplinas;
}

AvaliacaoMap &Dados::getAvaliacoes()
{
    return avaliacoes;
}

void Dados::setAvaliacoes(const AvaliacaoMap &avaliacoes)
{
    this->avaliacoes = avaliacoes;
}
