#include <iostream>
#include <string>
#include <map>

class Aluno
{
private:
    std::string nome;
    std::string Grad;
    std::map<std::string, double> notasProvas;

public:
    Aluno(const std::string &nome, const std::string &Grad)
        : nome(nome), Grad(Grad)
    {
    }

    std::string getNome() const
    {
        return nome;
    }

    std::map<std::string, double> getNotasAvaliacoes() const
    {
        return notasProvas;
    }
};
