#ifndef DISCIPLINA_MAP_HPP
#define DISCIPLINA_MAP_HPP

#include <string>
#include <map>
#include "Disciplina.hpp"

class DisciplinaMap
{
private:
    std::map<std::string, Disciplina> disciplinas;

public:
    std::map<std::string, Disciplina> &getDisciplinaMap();
};

#endif
