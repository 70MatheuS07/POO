#ifndef ALUNO_HPP
#define ALUNO_HPP

#include <string>
#include <unordered_map>

class Aluno {
private:
    std::string nome;
    std::string grad;
    std::unordered_map<std::string, double> notasProvas;

public:
    Aluno(const std::string& nome, const std::string& grad);
    std::string getNome() const;
    std::unordered_map<std::string, double> getNotasAvaliacoes() const;
};

#endif
