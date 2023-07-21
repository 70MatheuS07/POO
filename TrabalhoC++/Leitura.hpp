#ifndef LEITURA_HPP
#define LEITURA_HPP

#include <string>
#include <iostream>
#include <limits>
#include <algorithm>
#include <cctype>
#include <functional>
#include <locale>

class Leitura
{
public:
    static int leInteiro();
    static double leDouble();
    static std::string leLinha();
};

#endif