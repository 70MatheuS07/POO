#ifndef DISCIPLINA_MAP_HPP
#define DISCIPLINA_MAP_HPP

#include <string>
#include <map>
#include <vector>
#include <algorithm>
#include <iostream>
#include <fstream>
#include <iomanip>
#include <locale>
#include "Disciplina.hpp"
#include "AvaliacaoMap.hpp"
#include "AlunoMap.hpp"
#include "CursoMap.hpp"

class DisciplinaMap
{
private:
    std::map<std::string, Disciplina> disciplinas;

public:
    std::map<std::string, Disciplina> &getDisciplinaMap();

    void CriaPautaDisciplinas(AlunoMap alunos, AvaliacaoMap avaliacoes);

    void CriaDisciplinasCSV(AlunoMap alunos, AvaliacaoMap avaliacoes, CursoMap cursos);
};

#endif
