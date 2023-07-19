#include "AlunoGrad.hpp"

AlunoGrad::AlunoGrad(const std::string& nome, int curso) 
    : Aluno(nome), curso(curso) {}

int AlunoGrad::getCurso() const {
    return curso;
}
