#ifndef TRABALHO_HPP
#define TRABALHO_HPP

#include "Avaliacao.hpp"
#include "Excecao.hpp"

class Trabalho : public Avaliacao
{
private:
    int qtdGrupos;

public:
    Trabalho(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int qtdGrupos);
    int getQtdGrupos() const;

    void ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                            Avaliacao *avaliacao, std::string key_a, double value_NA) override;

    void WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(std::ofstream &writer, double value_avaliacao_aluno) override;

    Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(double value_np) const override;
};

#endif
