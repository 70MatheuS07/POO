#ifndef ALUNO_MAP_HPP
#define ALUNO_MAP_HPP

#include "Aluno.hpp"
#include <map>

class AlunoMap
{
public:
    std::map<int, Aluno> alunos;

    std::map<int, Aluno> &getAlunoMap();
};

#endif
