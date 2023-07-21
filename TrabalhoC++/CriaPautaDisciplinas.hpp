#ifndef CRIAPAUTADISCIPLINAS_H
#define CRIAPAUTADISCIPLINAS_H

#include "DisciplinaMap.hpp"
#include "AlunoMap.hpp"
#include "AvaliacaoMap.hpp"
#include "Excecao.hpp"
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <vector>
#include <algorithm>

class CriaPautaDisciplinas
{
public:
    static void CriaPautaDisciplinasFunction(DisciplinaMap *disciplinas, AlunoMap *alunos, AvaliacaoMap *avaliacoes);
};

#endif
