#ifndef CURSO_MAP_HPP
#define CURSO_MAP_HPP

#include <map>
#include "Curso.hpp"

class CursoMap
{
private:
    std::map<int, Curso> cursos;

public:
    std::map<int, Curso> &getCursoMap();
};

#endif
