#ifndef ALUNO_POS_HPP
#define ALUNO_POS_HPP

#include "Aluno.hpp"

class AlunoPos : public Aluno
{
public:
    static const int MESTRADO = 0;
    static const int DOUTORADO = 1;

private:
    int nivel;

public:
    AlunoPos(const std::string &nome, int nivel);
    int getNivel() const;

    /*

    void WriteAlunoGrad(Aluno *aluno, CursoMap &cursos,
                        std::map<std::string, int> &alunosGeral,
                        std::map<std::string, double> &mediaAlunos,
                        std::map<std::string, int> &alunosAprovados,
                        AvaliacaoMap &avaliacoes, std::string key_d) override;

                        */
};

#endif
