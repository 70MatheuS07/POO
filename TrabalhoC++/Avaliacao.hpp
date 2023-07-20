#ifndef AVALIACAO_HPP
#define AVALIACAO_HPP

#include <string>
#include <ctime>
#include <map>

class Avaliacao
{
private:
    std::string disciplinaKey;
    std::string nome;
    double peso;
    std::time_t data;

public:
    Avaliacao(const std::string &disciplina, const std::string &nome, double peso, std::time_t data);
    std::string getDisciplinaKey() const;
    std::string getNome() const;
    double getPeso() const;
    std::time_t getData() const;

    virtual void ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                                    Avaliacao *avaliacao, std::string key_a, double value_NA) = 0;

    virtual void WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno) = 0;

    class Valores_WriteValueAvaliacaoAluno
    {
    public:
        Valores_WriteValueAvaliacaoAluno(double total, double qtd, double prova_final);

        double getTotal() const;
        double getQtd() const;
        double getProva_final() const;

    private:
        double total;
        double qtd;
        double prova_final;
    };

    virtual Valores_WriteValueAvaliacaoAluno WriteValueAvaliacaoAluno(std::ofstream &writer, Avaliacao *avaliacao,
                                                                      double value_avaliacao_aluno) = 0;

    class Valores_WriteValueAvaliacaoAluno
    {
    public:
        Valores_WriteValueAvaliacaoAluno(double total, double qtd, double prova_final);

        double getTotal() const;
        double getQtd() const;
        double getProva_final() const;

    private:
        double total;
        double qtd;
        double prova_final;
    };

    virtual Valores_WriteValueAvaliacaoAluno CalculaMediasAluno(Avaliacao *avaliacao, double value_np) = 0;
};

#endif
