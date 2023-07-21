#include <iostream>
#include <string>
#include <vector>
#include "AlunoMap.hpp"
#include "CursoMap.hpp"
#include "DisciplinaMap.hpp"
#include "AvaliacaoMap.hpp"

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
    {
        if (args[i] == "-c")
        {
            i++;
            nomeArquivo = args[i];
            cursos->CadastrarCursos(nomeArquivo);
        }
        else if (args[i] == "-d")
        {
            i++;
            nomeArquivo = args[i];
            disciplinas->CadastrarDisciplinas(nomeArquivo);
        }
        else if (args[i] == "-a")
        {
            i++;
            nomeArquivo = args[i];
            // alunos->CadastrarAlunos(cursos, disciplinas, nomeArquivo);
        }
        else if (args[i] == "-p")
        {
            i++;
            nomeArquivo = args[i];
            // avaliacoes->CadastrarAvaliacoes(disciplinas, nomeArquivo);
        }
        else if (args[i] == "-n")
        {
            i++;
            nomeArquivo = args[i];
            // alunos->RegistraNotaAlunoAvaliacao(avaliacoes, disciplinas, nomeArquivo);
        }
    }

    delete cursos;
    delete disciplinas;
    delete alunos;
    delete avaliacoes;

    return 0;
}
