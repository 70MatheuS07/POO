#ifndef DISCIPLINAMAP_HPP
#define DISCIPLINAMAP_HPP

#include <map>
#include <string>
#include "Disciplina.hpp"

class DisciplinaMap {
private:
    std::map<std::string, Disciplina> disciplinas;

public:
    std::map<std::string, Disciplina>& getDisciplinaMap();
};

#endif
