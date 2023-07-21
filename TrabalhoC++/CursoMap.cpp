#include "CursoMap.hpp"

std::map<int, Curso> &CursoMap::getCursoMap()
{
    return cursos;
}

void CursoMap::CadastrarCursos(const std::string &arquivo)
{
    std::ifstream cursoFile(arquivo);

    if (!cursoFile.is_open())
    {
        throw Excecao("Erro ao abrir o arquivo.");
    }

    // Primeira linha é o cabeçalho.
    std::string linha = Leitura::LehLine(cursoFile);

    while (std::getline(cursoFile, linha))
    {
        std::istringstream ss(linha);
        std::string item;
        std::vector<std::string> dados;

        while (std::getline(ss, item, ';'))
        {
            dados.push_back(item);
        }

        int codigo = std::stoi(trim(dados[0]));
        if (cursos.find(codigo) != cursos.end())
        {
            throw CodigosIguaisException(codigo);
        }
        std::string nome = trim(dados[1]);

        Curso curso;
        curso.setCurso(nome);
        cursos[codigo] = curso;
    }

    cursoFile.close();
}