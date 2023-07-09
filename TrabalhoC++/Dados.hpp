#ifndef DADOS_H
#define DADOS_H

// Incluir os cabeçalhos das classes AlunoMap, CursoMap, DisciplinaMap e AvaliacaoMap
#include <iostream>
#include <string>
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "DisciplinaMap.hpp"
#include "AvaliacaoMap.hpp"

class Dados
{
private:
    AlunoMap *alunos;
    CursoMap *cursos;
    DisciplinaMap *disciplinas;
    AvaliacaoMap *provas;

public:
    AlunoMap *getAlunos() const;
    void setAlunos(AlunoMap *alunos);

    CursoMap *getCursos() const;
    void setCursos(CursoMap *cursos);

    DisciplinaMap *getDisciplinas() const;
    void setDisciplinas(DisciplinaMap *disciplinas);

    AvaliacaoMap *getAvaliacoes() const;
    void setAvaliacoes(AvaliacaoMap *provas);
};

#endif
