#ifndef LEITURA_HPP
#define LEITURA_HPP

#include <string>
#include <iostream>
#include <limits>

class Leitura
{
public:
    static int leInteiro();
    static double leDouble();
    static std::string leLinha();
};

#endif