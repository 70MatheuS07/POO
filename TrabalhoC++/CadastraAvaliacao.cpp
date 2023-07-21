#include "CadastraAvaliacao.hpp"



void CadastrarAvaliacao::CadastrarAvaliacoesFunction(AvaliacaoMap*avaliacoes,DisciplinaMap* disciplinas, const std::string& arquivo)
    {
    std::ifstream avaliacaoFile(arquivo);

    if (!avaliacaoFile.is_open())
    {
        throw ErroDeIO();
    }

    // Primeira linha é o cabeçalho.
    std::string linha = Leitura::LehLine(avaliacaoFile);

    Avaliacao *avaliacao;

    while (std::getline(avaliacaoFile, linha))
    {
        std::istringstream iss(linha);
        std::string dado;
        std::vector<std::string> dados;

        while (std::getline(iss, dado, ';'))
        {
            dados.push_back(dado);
        }

        std::string disciplina = dados[0];
        std::string codigo = dados[1];

        if (!disciplinas->getDisciplinaMap().count(disciplina))
        {
            throw CodDisciplinaIndefinidoAvalExcpetion(codigo, disciplina);
        }

        std::string nome = dados[2];
        double peso = std::stod(dados[3]);

        if (peso <= 0)
        {
            throw PesoZeroNegativo(codigo, peso);
        }

        std::string tipo = dados[4];
        std::tm data = {};
        std::istringstream dataStream(dados[5]);
        dataStream >> std::get_time(&data, "%d/%m/%Y");
        std::time_t dataTimestamp = std::mktime(&data);
        int tamMax;
        if ((tipo == "P" || tipo == "F") && dados.size() > 6)
        {
            tamMax = std::stoi(dados[6]);
            throw TamGrupoNaProvaException(codigo, tamMax);
        }

        if (tipo == "P")
        {
            avaliacao = new Prova(disciplina, nome, peso, dataTimestamp, Prova::PARCIAL);
        }
        else if (tipo == "F")
        {
            avaliacao = new Prova(disciplina, nome, peso, dataTimestamp, Prova::FINAL);
        }
        else if (tipo == "T")
        {
            tamMax = std::stoi(dados[6]);
            if (tamMax <= 0)
            {
                throw TamMaxZeroNegativo(codigo, tamMax);
            }
            avaliacao = new Trabalho(disciplina, nome, peso, dataTimestamp, tamMax);
        }
        else
        {
            throw NemPNemTException(codigo, tipo);
        }

        avaliacoes->getAvaliacaoMap().insert(std::make_pair(codigo , *avaliacao));
    }

    // confere se há alguma disciplina sem avaliações cadastrada
    for (const auto &entry : disciplinas->getDisciplinaMap())
    {
        bool boolVal = false;
        const std::string &CodD = entry.first;
        for (const auto &entry2 : avaliacoes->getAvaliacaoMap())
        {
            const Avaliacao &avaliacaoCompare = entry2.second;
            const std::string &CodDCompare = avaliacaoCompare.getDisciplinaKey();
            if (CodDCompare == CodD)
            {
                boolVal = true;
                break;
            }
        }
        if (!boolVal)
        {
            throw DisciplinaSemAvaliacaoException(CodD);
        }
    }
}