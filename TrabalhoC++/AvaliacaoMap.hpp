#ifndef AVALIACAOMAP_HPP
#define AVALIACAOMAP_HPP

#include <map>
#include <string>
#include "Avaliacao.hpp"

class AvaliacaoMap {
private:
    std::map<std::string, Avaliacao> avaliacoes;

public:
    std::map<std::string, Avaliacao>& getAvaliacaoMap();
};

#endif
