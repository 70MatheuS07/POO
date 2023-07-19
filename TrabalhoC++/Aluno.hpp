#ifndef ALUNO_HPP
#define ALUNO_HPP

#include <string>
#include <map>

class Aluno {
protected:
    std::string nome;
    std::map<std::string, double> notasProvas;

public:
    Aluno(const std::string& nome);
    std::string getNome() const;
    std::map<std::string, double> getNotasAvaliacoes() const;
};

#endif
