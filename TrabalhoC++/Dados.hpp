#ifndef DADOS_HPP
#define DADOS_HPP

#include "Aluno.hpp"
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "DisciplinaMap.hpp"
#include "AvaliacaoMap.hpp"

class Dados
{
private:
    AlunoMap alunos;
    CursoMap cursos;
    DisciplinaMap disciplinas;
    AvaliacaoMap avaliacoes;

public:
    AlunoMap &getAlunos();
    void setAlunos(const AlunoMap &alunos);
    CursoMap &getCursos();
    void setCursos(const CursoMap &cursos);
    DisciplinaMap &getDisciplinas();
    void setDisciplinas(const DisciplinaMap &disciplinas);
    AvaliacaoMap &getAvaliacoes();
    void setAvaliacoes(const AvaliacaoMap &avaliacoes);
};

#endif
