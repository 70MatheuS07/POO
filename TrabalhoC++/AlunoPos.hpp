#ifndef ALUNOPOS_HPP
#define ALUNOPOS_HPP

#include "Aluno.hpp"

class AlunoPos : public Aluno {
public:
    static const std::string MESTRADO;
    static const std::string DOUTORADO;

private:
    std::string nivel;

public:
    AlunoPos(const std::string& nome, const std::string& grad, const std::string& nivel);
    std::string getNivel() const;
};

#endif
