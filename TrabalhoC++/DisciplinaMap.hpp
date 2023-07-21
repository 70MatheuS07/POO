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
#include <sstream>

#include "Disciplina.hpp"
#include "AvaliacaoMap.hpp"
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "Excecao.hpp"

class DisciplinaMap
{
private:
    std::map<std::string, Disciplina> disciplinas;

public:
    std::map<std::string, Disciplina> &getDisciplinaMap();

    void CadastrarDisciplinas(const std::string &arquivo);


};

#endif
