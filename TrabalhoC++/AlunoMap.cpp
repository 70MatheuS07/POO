#include "AlunoMap.hpp"

std::map<int, Aluno> &AlunoMap::getAlunoMap()
{
    return alunos;
}

/*
void AlunoMap::CadastrarAlunos(CursoMap &cursos, DisciplinaMap &disciplinas, const std::string &arquivo)
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

        Aluno *aluno = nullptr;

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
            if (alunos.find(matricula) != alunos.end())
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

            for (const std::string &disciplina : disciplinasList)
            {
                if (disciplinas.getDisciplinaMap().find(disciplina) != disciplinas.getDisciplinaMap().end())
                {
                    throw CodDisciplinaIndefinidoAlunoExcpetion(matricula, disciplina);
                }
            }

            std::string tipo = dados[3];
            if (tipo == "G")
            {
                int curso = std::stoi(dados[4]);
                if (cursos.getCursoMap().find(curso) != cursos.getCursoMap().end())
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
            for (const std::string &disciplina : disciplinasList)
            {
                Disciplina &d = disciplinas.getDisciplinaMap().at(disciplina);
                d.getAlunoMap().getAlunoMap().emplace(matricula, aluno);
            }

            alunos.emplace(matricula, aluno);
        }

        disciplinaFile.close();
    }
    catch (const std::exception &e)
    {
        disciplinaFile.close();
        throw Excecao("Erro no processamento do arquivo.");
    }
}
*/

/*
void AlunoMap::RegistraNotaAlunoAvaliacao(AvaliacaoMap &avaliacoes, DisciplinaMap &disciplinas, const std::string &arquivo)
{
    std::ifstream disciplinaFile(arquivo);

    if (!disciplinaFile.is_open())
    {
        throw ErroDeIO();
    }

    // Primeira linha é o cabeçalho.
    std::string linha = Leitura::LehLine(disciplinaFile);

    while (std::getline(disciplinaFile, linha))
    {
        std::istringstream iss(linha);
        std::string dado;
        std::vector<std::string> dados;

        while (std::getline(iss, dado, ';'))
        {
            dados.push_back(dado);
        }

        std::string codigo = trim(dados[0]);
        // Tratamento da excecao codigo de avaliacao inexistente em sua planilha
        if (!avaliacoes.getAvaliacaoMap().count(codigo))
        {
            std::string matriculaErro;
            std::vector<std::string> MatriculasErro;
            std::string matriculasStringErro = dados[1];
            std::istringstream matriculasStream(matriculasStringErro);
            while (std::getline(matriculasStream, matriculaErro, ','))
            {
                MatriculasErro.push_back(matriculaErro);
            }
            std::string result = std::accumulate(MatriculasErro.begin(), MatriculasErro.end(), std::string(),
                                                 [](const std::string &s1, const std::string &s2)
                                                 { return s1 + ", " + s2; });
            throw CodAvaliacaoIndefinidoException(result, codigo);
        }

        // Pega codigo da avaliacao a partir do mapa de avaliacoes
        Avaliacao avaliacao = avaliacoes.getAvaliacaoMap().at(codigo);
        // Pega o mapa de alunos da disciplina em que a avaliacao ocorreu
        Disciplina disciplina = disciplinas.getDisciplinaMap().at(avaliacao.getDisciplinaKey());
        AlunoMap mapaAlunos = disciplina.getAlunoMap();

        std::string doubleString = trim(dados[2]);
        doubleString = doubleString.replace(',', '.');
        double nota = std::stod(doubleString);

        if (!avaliacoes.getTotalNotasIO().count(codigo))
        {
            avaliacoes.getQtdNotasIO().insert({codigo, 1});
            avaliacoes.getTotalNotasIO().insert({codigo, nota});
        }
        else
        {
            int currentValueInteger = avaliacoes.getQtdNotasIO().at(codigo);
            avaliacoes.getQtdNotasIO().at(codigo) = currentValueInteger + 1;

            double currentValueDouble = avaliacoes.getTotalNotasIO().at(codigo);
            avaliacoes.getTotalNotasIO().at(codigo) = currentValueDouble + nota;
        }

        if (nota < 0 || nota > 10)
        {
            std::string matriculaErroN;
            std::vector<std::string> MatriculasErroN;
            std::string matriculasStringErroN = dados[1];
            std::istringstream matriculasStreamN(matriculasStringErroN);
            while (std::getline(matriculasStreamN, matriculaErroN, ','))
            {
                MatriculasErroN.push_back(matriculaErroN);
            }
            std::string result = std::accumulate(MatriculasErroN.begin(), MatriculasErroN.end(), std::string(),
                                                 [](const std::string &s1, const std::string &s2)
                                                 { return s1 + ", " + s2; });

            std::stringstream ss;
            ss << std::fixed << std::setprecision(1) << nota;
            std::string formattedMedia = ss.str();
            throw NotaInvalidaAvaliacaoException(result, codigo, formattedMedia);
        }

        avaliacao.TratamentoExcecoes(dados, alunos, mapaAlunos, codigo, avaliacao, nota);
    }
}
*/