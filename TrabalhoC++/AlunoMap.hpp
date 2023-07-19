#ifndef ALUNO_MAP_HPP
#define ALUNO_MAP_HPP

#include <map>
#include "Aluno.hpp"

class AlunoMap
{
public:
    std::map<int, Aluno> alunos;

    std::map<int, Aluno> &getAlunoMap();
};

#endif
