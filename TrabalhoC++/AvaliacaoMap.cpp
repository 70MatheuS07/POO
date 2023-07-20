#include "AvaliacaoMap.hpp"

std::map<std::string, Avaliacao> &AvaliacaoMap::getAvaliacaoMap()
{
    return avaliacoes;
}

std::map<std::string, int> AvaliacaoMap::getQtdNotasIO() const
{
    return qtdNotasIO;
}

void AvaliacaoMap::setQtdNotasIO(const std::map<std::string, int> &qtdNotasIO)
{
    this->qtdNotasIO = qtdNotasIO;
}

std::map<std::string, double> AvaliacaoMap::getTotalNotasIO() const
{
    return totalNotasIO;
}

void AvaliacaoMap::setTotalNotasIO(const std::map<std::string, double> &totalNotasIO)
{
    this->totalNotasIO = totalNotasIO;
}
