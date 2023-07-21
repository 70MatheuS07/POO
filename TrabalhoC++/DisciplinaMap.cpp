#include "DisciplinaMap.hpp"

std::map<std::string, Disciplina> &DisciplinaMap::getDisciplinaMap()
{
    return disciplinas;
}

void DisciplinaMap::CadastrarDisciplinas(const std::string &arquivo)
{
    std::ifstream disciplinaFile(arquivo);

    if (!disciplinaFile.is_open())
    {
        throw Excecao("Erro ao abrir o arquivo.");
    }

    // Primeira linha é o cabeçalho.
    std::string linha;
    linha = Leitura::LehLine(disciplinaFile); // Supondo que você tenha um método LehLine definido na classe Leitura

    while (std::getline(disciplinaFile, linha))
    {
        std::istringstream ss(linha);
        std::string item;
        std::vector<std::string> dados;

        while (std::getline(ss, item, ';'))
        {
            dados.push_back(item);
        }

        std::string codigo = trim(dados[0]);
        std::string nome = trim(dados[1]);

        Disciplina disciplina;
        disciplina.setDisciplina(nome);
        disciplinas[codigo] = disciplina;
    }

    disciplinaFile.close();
}

