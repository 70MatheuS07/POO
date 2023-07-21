#ifndef CURSO_MAP_HPP
#define CURSO_MAP_HPP

#include <map>
#include <vector>
#include <string>
#include <vector>
#include <iostream>
#include <sstream>
#include <fstream>

#include "Curso.hpp"
#include "Excecao.hpp"
#include "Leitura.hpp"
#include "Trim.hpp"

class CursoMap
{
private:
    std::map<int, Curso> cursos;

public:
    std::map<int, Curso> &getCursoMap();
    void CadastrarCursos(const std::string &arquivo);
};

#endif
