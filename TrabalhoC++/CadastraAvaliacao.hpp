#ifndef CADASTRAAVALIACAO_MAP_HPP
#define CADASTRAAVALIACAO_MAP_HPP

#include <string>
#include <fstream>
#include <sstream>
#include <map>
#include <vector>
#include <ctime>
#include "Trim.hpp"
#include "Prova.hpp"
#include "Trabalho.hpp"
#include "DisciplinaMap.hpp"
#include "AvaliacaoMap.hpp"
#include "Excecao.hpp"

class CadastrarAvaliacao {
    
public:
   static void CadastrarAvaliacoesFunction(AvaliacaoMap*avaliacoes,DisciplinaMap* disciplinas, const std::string& arquivo);
};
#endif // AVALIACAO_MAP_HPP
