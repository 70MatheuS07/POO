#ifndef ALUNOMAP_HPP
#define ALUNOMAP_HPP

#include <map>
#include <iostream>
#include "Aluno.hpp"

class AlunoMap {
private:
    std::map<int, Aluno> alunos;

public:
    std::map<int, Aluno>& getAlunoMap();
};

#endif
