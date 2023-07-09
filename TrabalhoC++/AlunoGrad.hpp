#ifndef ALUNOGRAD_HPP
#define ALUNOGRAD_HPP

#include "Aluno.hpp"

class AlunoGrad : public Aluno {
private:
    int curso;

public:
    AlunoGrad(const std::string& nome, const std::string& grad, int curso);
    int getCurso() const;
};

#endif
