#include "Leitura.hpp"

int Leitura::LehInt(std::istream& input) {
    int inteiro;
    input >> inteiro;
    input.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return inteiro;
}

double Leitura::LehDouble(std::istream& input) {
    double valor;
    input >> valor;
    input.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    return valor;
}

std::string Leitura::LehLine(std::istream& input) {
    std::string linha;
    std::getline(input, linha);
    return linha;
}
