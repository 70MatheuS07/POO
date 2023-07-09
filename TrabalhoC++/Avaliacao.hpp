#ifndef AVALIACAO_HPP
#define AVALIACAO_HPP

#include <string>
#include <ctime>

class Avaliacao {
private:
    std::string disciplinaKey;
    std::string nome;
    double peso;
    std::time_t data;

public:
    Avaliacao(const std::string& disciplina, const std::string& nome, double peso, std::time_t data);
    std::string getDisciplinaKey() const;
    std::string getNome() const;
    double getPeso() const;
    std::time_t getData() const;
};

#endif
