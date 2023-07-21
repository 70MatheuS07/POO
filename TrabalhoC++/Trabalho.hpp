#ifndef TRABALHO_HPP
#define TRABALHO_HPP

#include "Avaliacao.hpp"
#include "Excecao.hpp"
#include <locale>
#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include "Trim.hpp"
#include <vector>
#include <climits>
#include <string>

class Trabalho : public Avaliacao
{
private:
    int qtdGrupos;

public:
    Trabalho(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int qtdGrupos);
    int getQtdGrupos() const;

    /*

    void ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                            Avaliacao *avaliacao, std::string key_a, double value_NA) override;

    void WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(std::ofstream &writer, Avaliacao *avaliacao,
                                                              double value_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao *avaliacao, double value_np) override;

    */

    /*
        void TratamentoExcecoes(std::string dados[], std::map<int, Aluno> &alunos, AlunoMap &mapaAlunos,
                                std::string codigo, Avaliacao avaliacao, double nota);
                                */
};

#endif
