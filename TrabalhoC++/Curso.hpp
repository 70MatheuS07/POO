#ifndef CURSO_HPP
#define CURSO_HPP

#include <string>


class Curso
{
private:
    std::string nome;

public:
    void setCurso(const std::string &nome);
    std::string getNome() const;
};

#endif
