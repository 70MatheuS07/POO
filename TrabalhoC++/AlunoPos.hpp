#ifndef ALUNO_POS_HPP
#define ALUNO_POS_HPP

#include "Aluno.hpp" // Supondo que já existe um arquivo Aluno.hpp para a classe Aluno

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
};

#endif // ALUNO_POS_HPP
