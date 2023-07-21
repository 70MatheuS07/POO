#ifndef ALUNO_MAP_HPP
#define ALUNO_MAP_HPP

#include "Aluno.hpp"
#include <map>
#include "Excecao.hpp"
#include <sstream>
#include <iostream>

class AlunoMap
{
public:
    std::map<int, Aluno> alunos;

    std::map<int, Aluno> &getAlunoMap();

    // void CadastrarAlunos(CursoMap &cursos, DisciplinaMap &disciplinas, const std::string &arquivo);
};

#endif
