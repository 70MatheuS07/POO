#ifndef TRATAMENTO_EXCECOES_HPP
#define TRATAMENTO_EXCECOES_HPP

#include "Excecao.hpp"
#include "AlunoMap.hpp"
#include "Avaliacao.hpp"
#include <vector>

class TratamentoExcecoes
{
public:
    static void TratamentoExcecoesProva(const std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                   const std::string &codigo, Avaliacao *avaliacao, double nota) throw(Excecao);

    static void TratamentoExcecoesTrabalho(const std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                   const std::string &codigo, Avaliacao *avaliacao, double nota,
                                   const std::vector<int> &matriculas) throw(Excecao);
};

#endif
