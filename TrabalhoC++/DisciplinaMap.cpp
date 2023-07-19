#include "DisciplinaMap.hpp"

std::map<std::string, Disciplina> &DisciplinaMap::getDisciplinaMap()
{
    return disciplinas;
}
