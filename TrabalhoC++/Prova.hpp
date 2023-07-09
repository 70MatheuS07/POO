#ifndef PROVA_HPP
#define PROVA_HPP

#include "Avaliacao.hpp"
#include <string>
#include <ctime>

class Prova : public Avaliacao {
private:
    bool tipoProva;

public:
    Prova(const std::string& disciplina, const std::string& nome, double peso, const std::time_t& data, bool tipoProva);

    bool getTipoProva() const;
};

#endif
