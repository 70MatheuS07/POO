#ifndef CRIADISCIPLINASCSV_HPP
#define CRIADISCIPLINASCSV_HPP

#include <fstream>
#include <iostream>
#include <iomanip>
#include <locale>
#include <map>
#include <string>
#include <vector>
#include <algorithm>

#include "AlunoMap.hpp"
#include "AvaliacaoMap.hpp"
#include "CursoMap.hpp"
#include "Excecao.hpp"
#include "DisciplinaMap.hpp"

class CriaDisciplinasCSV
{
public:
    static void CriaDisciplinasCSVFunction(DisciplinaMap *disciplinas, AlunoMap *alunos, AvaliacaoMap *avaliacoes, CursoMap *cursos);
};

#endif
