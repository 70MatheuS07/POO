#include <iostream>
#include <string>
#include <vector>
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "DisciplinaMap.hpp"
#include "AvaliacaoMap.hpp"
#include "CriaAvaliacoesCSV.hpp"
#include "CriaDisciplinasCSV.hpp"
#include "CriaPautaDisciplinas.hpp"
#include "CadastraAlunos.hpp"
#include "CadastraAvaliacao.hpp"

int main(int argc, char *argv[])
{
    AlunoMap *alunos = new AlunoMap();
    CursoMap *cursos = new CursoMap();
    DisciplinaMap *disciplinas = new DisciplinaMap();
    AvaliacaoMap *avaliacoes = new AvaliacaoMap();

    std::vector<std::string> args(argv, argv + argc);

    std::string nomeArquivo;

    // Esse vector tira o warning que aparecee no terminal.
    for (std::vector<int>::size_type i = 0; i < args.size(); i++)
    {            // Implementado
        if (args[i] == "-c")
        {
            i++;
            nomeArquivo = args[i];
            // Implementado
            cursos->CadastrarCursos(nomeArquivo);
        }
        else if (args[i] == "-d")
        {
            i++;
            nomeArquivo = args[i];
            // Implementado
            disciplinas->CadastrarDisciplinas(nomeArquivo);
        }
        else if (args[i] == "-a")
        {
            i++;
            nomeArquivo = args[i];
            // Implementado
              CadastraAlunos::CadastrarAlunosFunction(alunos,cursos, disciplinas, nomeArquivo);
        }
        else if (args[i] == "-p")
        {
            i++;
            nomeArquivo = args[i];
            // Implementado
            CadastrarAvaliacao::CadastrarAvaliacoesFunction(avaliacoes,disciplinas, nomeArquivo);
        }
        else if (args[i] == "-n")
        {
            i++;
            nomeArquivo = args[i];
            //criariamos tambem o registranotaluno como classe para evitar a dependencia circular
            // alunos->RegistraNotaAlunoAvaliacao(avaliacoes, disciplinas, nomeArquivo);
        }
    }

    CriaPautaDisciplinas::CriaPautaDisciplinasFunction(disciplinas, alunos, avaliacoes);
    CriaDisciplinasCSV::CriaDisciplinasCSVFunction(disciplinas, alunos, avaliacoes, cursos);
    CriaAvaliacoesCSV::CriaAvaliacoesCSVFunction(avaliacoes, disciplinas, alunos);

    delete cursos;
    delete disciplinas;
    delete alunos;
    delete avaliacoes;

    return 0;
}
