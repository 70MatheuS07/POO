#ifndef ALUNO_GRAD_HPP
#define ALUNO_GRAD_HPP

#include "Aluno.hpp"

class AlunoGrad : public Aluno {
private:
    int curso;

public:
    AlunoGrad(const std::string& nome, int curso);
    int getCurso() const;
};

#endif
