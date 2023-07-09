#include "Empacotamento.hpp"
#include <iostream>
#include <fstream>

void GravarArquivoBinario(const std::string& nomeArquivo, const Dados& dados) {
    std::ofstream arquivo(nomeArquivo, std::ios::binary);
    if (arquivo.is_open()) {
        arquivo.write(reinterpret_cast<const char*>(&dados), sizeof(Dados));
        arquivo.close();
        std::cout << "Objetos serializados e salvos no arquivo " << nomeArquivo << std::endl;
    } else {
        std::cerr << "Erro ao abrir o arquivo para salvar os objetos." << std::endl;
    }
}

Dados LerArquivoBinario(const std::string& nomeArquivo) {
    Dados dados;

    std::ifstream arquivo(nomeArquivo, std::ios::binary);
    if (arquivo.is_open()) {
        arquivo.read(reinterpret_cast<char*>(&dados), sizeof(Dados));
        arquivo.close();
    } else {
        std::cerr << "Erro ao abrir o arquivo para ler os objetos." << std::endl;
    }

    return dados;
}
