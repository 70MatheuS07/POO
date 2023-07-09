#include "Avaliacao.hpp"

Avaliacao::Avaliacao(const std::string& disciplina, const std::string& nome, double peso, std::time_t data)
    : disciplinaKey(disciplina), nome(nome), peso(peso), data(data) {}

std::string Avaliacao::getDisciplinaKey() const {
    return disciplinaKey;
}

std::string Avaliacao::getNome() const {
    return nome;
}

double Avaliacao::getPeso() const {
    return peso;
}

std::time_t Avaliacao::getData() const {
    return data;
}
