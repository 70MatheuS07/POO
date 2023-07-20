#include "TratamentoExcecoes.hpp"

void TratamentoExcecoes::TratamentoExcecoesProva(const std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                                 const std::string &codigo, Avaliacao *avaliacao, double nota) throw(Excecao)
{
    long matriculaLong = std::stol(dados[1].trim());

    // Tratamento de uma matricula fora do escopo inteiro.
    if (matriculaLong > INT_MAX)
    {
        throw Excecao("MatriculaIndefinidaLongException: Codigo: " + codigo + ", Matricula: " + std::to_string(matriculaLong));
    }

    int matricula = std::stoi(dados[1].trim());

    // Tratamento de uma matricula fora do escopo inteiro.
    if (!alunos.containsKey(matricula))
    {
        throw Excecao("MatriculaIndefinidaException: Codigo: " + codigo + ", Matricula: " + std::to_string(matricula));
    }

    if (!mapaAlunos.alunos.containsKey(matricula))
    {
        throw Excecao("AlunoNaoMatriculadoException: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo +
                      ", DisciplinaKey: " + avaliacao->getDisciplinaKey());
    }

    if (alunos.get(matricula).notasProvas.containsKey(codigo))
    {
        throw Excecao("NotaDuplicada: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo);
    }

    Aluno &aluno = alunos.get(matricula);
    aluno.getNotasAvaliacoes().put(codigo, nota);
}

void TratamentoExcecoes::TratamentoExcecoesTrabalho(const std::string *dados, AlunoMap &alunos, const AlunoMap &mapaAlunos,
                                                    const std::string &codigo, Avaliacao *avaliacao, double nota,
                                                    const std::vector<int> &matriculas) throw(Excecao)
{
    for (int matricula : matriculas)
    {
        if (matricula > INT_MAX)
        {
            throw Excecao("MatriculaIndefinidaLongException: Codigo: " + codigo + ", Matricula: " + std::to_string(matricula));
        }

        if (!alunos.containsKey(matricula))
        {
            throw Excecao("MatriculaIndefinidaException: Codigo: " + codigo + ", Matricula: " + std::to_string(matricula));
        }

        if (!mapaAlunos.alunos.containsKey(matricula))
        {
            throw Excecao("AlunoNaoMatriculadoException: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo +
                          ", DisciplinaKey: " + avaliacao->getDisciplinaKey());
        }

        if (alunos.get(matricula).notasProvas.containsKey(codigo))
        {
            throw Excecao("NotaDuplicada: Matricula: " + std::to_string(matricula) + ", Codigo: " + codigo);
        }

        Aluno &aluno = alunos.get(matricula);
        aluno.getNotasAvaliacoes().put(codigo, nota);
    }
}
