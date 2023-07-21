#ifndef ALUNO_HPP
#define ALUNO_HPP

#include <string>
#include <map>
#include "CursoMap.hpp"
#include "AvaliacaoMap.hpp"

class Aluno
{
protected:
    std::string nome;
    std::map<std::string, double> notasProvas;

public:
    Aluno(const std::string &nome);
    std::string getNome() const;
    std::map<std::string, double> getNotasAvaliacoes() const;

    virtual void WriteAlunoGrad(Aluno *aluno, CursoMap &cursos,
                                std::map<std::string, int> &alunosGeral,
                                std::map<std::string, double> &mediaAlunos,
                                std::map<std::string, int> &alunosAprovados,
                                AvaliacaoMap &avaliacoes,
                                std::string key_d) = 0;
};

#endif
