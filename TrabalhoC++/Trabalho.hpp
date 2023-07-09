#ifndef TRABALHO_HPP
#define TRABALHO_HPP

#include "Avaliacao.hpp"
#include <string>
#include <ctime>

class Trabalho : public Avaliacao
{
private:
    int qtdGrupos;

public:
    Trabalho(const std::string &disciplina, const std::string &nome, double peso, const std::time_t &data, int qtdGrupos);

    int getQtdGrupos() const;
};

#endif
