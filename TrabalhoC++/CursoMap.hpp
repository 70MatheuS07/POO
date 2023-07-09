#ifndef CURSOMAP_HPP
#define CURSOMAP_HPP

#include <map>
#include "Curso.hpp"

class CursoMap {
private:
    std::map<int, Curso> cursos;

public:
    std::map<int, Curso>& getCursoMap();
};

#endif
