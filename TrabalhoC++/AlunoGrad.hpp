#ifndef ALUNO_GRAD_HPP
#define ALUNO_GRAD_HPP

#include "Aluno.hpp"
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "AvaliacaoMap.hpp"
#include "Prova.hpp"
#include "Trabalho.hpp"
#include <string>
#include <vector>
#include <map>

class AlunoGrad : public Aluno
{
private:
    int curso;

public:
    AlunoGrad(const std::string &nome, int curso);
    int getCurso() const;

    void WriteAlunoGrad(Aluno *aluno, CursoMap &cursos,
                        std::map<std::string, int> &alunosGeral,
                        std::map<std::string, double> &mediaAlunos,
                        std::map<std::string, int> &alunosAprovados,
                        AvaliacaoMap &avaliacoes, std::string key_d) override;
};

#endif
