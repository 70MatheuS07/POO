#include "Trabalho.hpp"

Trabalho::Trabalho(const std::string &disciplina, const std::string &nome, double peso, std::time_t data, int qtdGrupos)
    : Avaliacao(disciplina, nome, peso, data), qtdGrupos(qtdGrupos) {}

int Trabalho::getQtdGrupos() const
{
    return qtdGrupos;
}

/*
void Trabalho::ModificaMapasNotas(std::map<std::string, int> &qtdNotas, std::map<std::string, double> &totalNotas,
                                  Avaliacao *avaliacao, std::string key_a, double value_NA)
{

    if (totalNotas.find(key_a) != totalNotas.end())
    {
        qtdNotas[key_a] = 1;
        totalNotas[key_a] = value_NA;
    }
    else
    {
        qtdNotas[key_a]++;
        totalNotas[key_a] += value_NA;
    }
}

void Trabalho::WriteKeyAvaliacao(std::ofstream &writer, Avaliacao *avaliacao, std::string key_avaliacao_aluno)
{
    try
    {
        writer << key_avaliacao_aluno << ";";
    }
    catch (const std::exception &e)
    {
        throw ErroDeIO();
    }
}

Avaliacao::Valores_WriteValueAvaliacaoAluno Trabalho::WriteValueAvaliacaoAluno(std::ofstream &writer, Avaliacao *avaliacao,
                                                                               double value_avaliacao_aluno)
{
    try
    {
        std::locale localeGerman("German");
        writer.imbue(localeGerman);

        std::string formattedTotal = std::to_string(value_avaliacao_aluno);
        writer << formattedTotal << ";";

        double total = value_avaliacao_aluno * getPeso();
        double qtd_prov_trab = getPeso();

        return Avaliacao::Valores_WriteValueAvaliacaoAluno(total, qtd_prov_trab, -1.0);
    }
    catch (std::ofstream::failure &e)
    {
        throw Excecao("ErroDeIO");
    }
}

Avaliacao::Valores_WriteValueAvaliacaoAluno Trabalho::CalculaMediasAluno(Avaliacao *avaliacao, double value_np)
{
    double total_notas = value_np * getPeso();
    double qtd_notas = getPeso();

    return Valores_WriteValueAvaliacaoAluno(total_notas, qtd_notas, -1.0);
}

*/

/*
void Trabalho::TratamentoExcecoes(std::string dados[], std::map<int, Aluno> &alunos, AlunoMap &mapaAlunos,
                                  std::string codigo, Avaliacao avaliacao, double nota)
{
    std::vector<int> matriculas;

    // loop para ler as matriculas dos alunos, armazenando em uma lista
    std::istringstream iss(trim(dados[1]));
    std::string matriculaString;
    while (std::getline(iss, matriculaString, ','))
    {
        long matriculaLong = std::stol(matriculaString);

        // Tratamento de uma matricula fora do escopo inteiro.
        if (matriculaLong > INT_MAX)
        {
            throw Excecao("MatriculaIndefinidaLongException: Codigo: " + codigo + ", Matricula: " + std::to_string(matriculaLong));
        }

        int matricula = std::stoi(matriculaString);

        if (alunos.find(matricula) == alunos.end())
        {
            throw MatriculaIndefinidaException(codigo, matricula);
        }

        // confere se o aluno está no mapa de alunos da disciplina
        if (mapaAlunos.alunos.find(matricula) == mapaAlunos.alunos.end())
        {
            throw AlunoNaoMatriculadoException(matricula, codigo,
                                               avaliacao.getDisciplinaKey());
        }
        if (alunos[matricula].getNotasAvaliacoes().find(codigo) != alunos[matricula].getNotasAvaliacoes().end())
        {
            throw NotaDuplicada(matricula, codigo);
        }

        matriculas.push_back(matricula);
    }

    for (int m : matriculas)
    {
        alunos[m].getNotasAvaliacoes()[codigo] = nota;
    }
}

*/