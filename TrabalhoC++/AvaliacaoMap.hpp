#ifndef AVALIACAO_MAP_HPP
#define AVALIACAO_MAP_HPP

#include <string>
#include <map>
#include <vector>

#include "Avaliacao.hpp"

class AvaliacaoMap
{
private:
    std::map<std::string, Avaliacao> avaliacoes;

public:
    std::map<std::string, Avaliacao> &getAvaliacaoMap();

    std::map<std::string, int> getQtdNotasIO() const;
    void setQtdNotasIO(const std::map<std::string, int> &qtdNotasIO);

    std::map<std::string, double> getTotalNotasIO() const;
    void setTotalNotasIO(const std::map<std::string, double> &totalNotasIO);
};

#endif
