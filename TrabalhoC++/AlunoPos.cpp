#include "AlunoPos.hpp"

const std::string AlunoPos::MESTRADO = "Mestrado";
const std::string AlunoPos::DOUTORADO = "Doutorado";

AlunoPos::AlunoPos(const std::string& nome, const std::string& grad, const std::string& nivel)
    : Aluno(nome, grad), nivel(nivel) {}

std::string AlunoPos::getNivel() const {
    return nivel;
}
