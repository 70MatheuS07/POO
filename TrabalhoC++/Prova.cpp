#include "Prova.hpp"

Prova::Prova(const std::string& disciplina, const std::string& nome, double peso, const std::time_t& data, bool tipoProva)
    : Avaliacao(disciplina, nome, peso, data), tipoProva(tipoProva) {}

bool Prova::getTipoProva() const {
    return tipoProva;
}
