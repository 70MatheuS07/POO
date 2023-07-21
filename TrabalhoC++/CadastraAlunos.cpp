#include "CadastraAlunos.hpp"
#include <fstream>
#include <sstream>

void CadastraAlunos::CadastrarAlunosFunction(AlunoMap* alunos, CursoMap* cursos, DisciplinaMap* disciplinas, const std::string& arquivo)
{
    std::ifstream disciplinaFile(arquivo);

    if (!disciplinaFile.is_open())
    {
        throw Excecao("Erro ao abrir o arquivo.");
    }

    try
    {
        // Primeira linha é o cabeçalho.
        std::string linha;
        linha = Leitura::LehLine(disciplinaFile); // Supondo que você tenha um método LehLine definido na classe Leitura

        Aluno* aluno = nullptr;

        while (std::getline(disciplinaFile, linha))
        {
            std::istringstream ss(linha);
            std::string item;
            std::vector<std::string> dados;

            while (std::getline(ss, item, ';'))
            {
                dados.push_back(item);
            }

            int matricula = std::stoi(dados[0]);
            if (alunos->getAlunoMap().find(matricula) != alunos->getAlunoMap().end())
            {
                throw MatriculasIguaisException(matricula);
            }

            std::string nome = dados[1];
            std::string disciplinasCSV = dados[2];
            std::vector<std::string> disciplinasList;

            // Desconsidera espaços em branco
            std::istringstream iss(disciplinasCSV);
            std::string disciplina;
            while (std::getline(iss, disciplina, ','))
            {
                disciplinasList.push_back(disciplina);
            }

            for (const std::string& disciplina : disciplinasList)
            {
                if (disciplinas->getDisciplinaMap().find(disciplina) != disciplinas->getDisciplinaMap().end())
                {
                    throw CodDisciplinaIndefinidoAlunoExcpetion(matricula, disciplina);
                }
            }

            std::string tipo = dados[3];
            if (tipo == "G")
            {
                int curso = std::stoi(dados[4]);
                if (cursos->getCursoMap().find(curso) != cursos->getCursoMap().end())
                {
                    throw CodCursoIndefinidoException(matricula, curso);
                }
                aluno = new AlunoGrad(nome, curso);
            }
            else if (tipo == "P")
            {
                std::string curso = dados[4];
                if (curso == "M")
                {
                    aluno = new AlunoPos(nome, AlunoPos::MESTRADO);
                }
                else if (curso == "D")
                {
                    aluno = new AlunoPos(nome, AlunoPos::DOUTORADO);
                }
                else
                {
                    throw NemMNemDException(matricula, curso);
                }
            }
            else
            {
                throw NemGNemPException(matricula, tipo);
            }

            // Adiciona o aluno nas disciplinas
            for (const std::string& disciplina : disciplinasList)
            {
                Disciplina& d = disciplinas->getDisciplinaMap().at(disciplina);
                d.getAlunoMap().getAlunoMap().insert(std::make_pair(matricula, *aluno));
            }

            alunos->getAlunoMap().insert(std::make_pair(matricula, *aluno));
        }

        disciplinaFile.close();
    }
    catch (const std::exception& e)
    {
        disciplinaFile.close();
        throw Excecao("Erro no processamento do arquivo.");
    }
}
