#ifndef ALUNO_MAP_HPP
#define ALUNO_MAP_HPP

#include <map>
#include <sstream>
#include <iostream>
#include <vector>
#include <string>

#include "Aluno.hpp"
#include "Excecao.hpp"
#include "Leitura.hpp"
// #include "DisciplinaMap.hpp"
// #include "CursoMap.hpp"

class AlunoMap
{
public:
    std::map<int, Aluno> alunos;

    std::map<int, Aluno> &getAlunoMap();

    //void CadastrarAlunos(CursoMap &cursos, DisciplinaMap &disciplinas, const std::string &arquivo);
};

#endif
