#include "AlunoGrad.hpp"

AlunoGrad::AlunoGrad(const std::string& nome, const std::string& grad, int curso)
    : Aluno(nome, grad), curso(curso) {}

int AlunoGrad::getCurso() const {
    return curso;
}
