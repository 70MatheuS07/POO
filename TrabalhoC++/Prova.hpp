#ifndef PROVA_HPP
#define PROVA_HPP

#include "Avaliacao.hpp"
#include "Excecao.hpp"

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

    void ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                            Avaliacao *avaliacao, std::string key_a, double value_NA) override;

    void WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) override;
};

#endif
