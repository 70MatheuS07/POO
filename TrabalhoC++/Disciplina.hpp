#ifndef DISCIPLINA_HPP
#define DISCIPLINA_HPP

#include <string>

#include "AlunoMap.hpp"

class Disciplina
{
private:
    std::string nome;
    AlunoMap alunos;

public:
    AlunoMap &getAlunoMap();
    const AlunoMap &getAlunoMap() const;
    void setDisciplina(const std::string &nome);
    std::string getNome() const;
};

#endif
