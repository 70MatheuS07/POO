#include <iostream>
#include <string>
#include <unordered_map>

class Aluno
{
protected:
    std::string nome;
    std::string Grad;
    std::unordered_map<std::string, double> notasProvas;

public:
    Aluno(const std::string &nome, const std::string &Grad)
        : nome(nome), Grad(Grad)
    {
    }

    std::string getNome() const
    {
        return nome;
    }

    std::unordered_map<std::string, double> getNotasAvaliacoes() const
    {
        return notasProvas;
    }
};
