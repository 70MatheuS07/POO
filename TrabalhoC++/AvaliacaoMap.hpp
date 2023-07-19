#ifndef AVALIACAO_MAP_HPP
#define AVALIACAO_MAP_HPP

#include <string>
#include <map>
#include <vector>
#include "Avaliacao.hpp" // Supondo que já existe um arquivo Avaliacao.hpp para a classe Avaliacao

class AvaliacaoMap
{
private:
    std::map<std::string, Avaliacao> avaliacoes;
    std::map<std::string, int> qtdNotasIO;
    std::map<std::string, double> totalNotasIO;

public:
    std::map<std::string, Avaliacao> &getAvaliacaoMap();
    std::map<std::string, int> &getQtdNotasIO();
    void setQtdNotasIO(const std::map<std::string, int> &qtdNotasIO);
    std::map<std::string, double> &getTotalNotasIO();
    void setTotalNotasIO(const std::map<std::string, double> &totalNotasIO);
};

#endif
