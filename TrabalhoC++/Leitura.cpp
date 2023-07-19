#include "Leitura.hpp"

int Leitura::leInteiro()
{
    int inteiro;
    std::cin >> inteiro;
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return inteiro;
}

double Leitura::leDouble()
{
    double valor;
    std::cin >> valor;
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return valor;
}

std::string Leitura::leLinha()
{
    std::string linha;
    std::getline(std::cin, linha);
    return linha;
}
