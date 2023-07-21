#ifndef PROVA_HPP
#define PROVA_HPP

#include <fstream>
#include <sstream>
#include <iomanip>
#include <vector>
#include <climits>
#include <string>
#include <sstream>

#include "Avaliacao.hpp"
#include "Excecao.hpp"
#include "Trim.hpp"

class Prova : public Avaliacao
{
public:
    static const int PARCIAL = 0;
    static const int FINAL = 1;

private:
    int tipoProva;

public:
    Prova(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int tipoProva);
    int getTipoProva() const;

    /*

    void ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                            Avaliacao *avaliacao, std::string key_a, double value_NA) override;

    void WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(std::ofstream &writer, Avaliacao *avaliacao,
                                                                         double value_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao *avaliacao, double value_np) override;

    */

    /*
        void Prova::TratamentoExcecoes(std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                       const std::string &codigo, Avaliacao *avaliacao, double nota);
                                       */
};

#endif
