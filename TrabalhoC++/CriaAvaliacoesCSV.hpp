#ifndef CRIA_AVALIACOES_CSV_HPP
#define CRIA_AVALIACOES_CSV_HPP

#include <fstream>
#include <sstream>
#include <iomanip>
#include <algorithm>
#include <ctime>
#include <chrono>

#include "DisciplinaMap.hpp"
#include "AlunoMap.hpp"
#include "Avaliacao.hpp"
#include "Excecao.hpp"

class CriaAvaliacoesCSV
{
public:
    static void CriaAvaliacoesCSVFunction(AvaliacaoMap *avaliacoes, DisciplinaMap *disciplinas, AlunoMap *alunos);
};

#endif