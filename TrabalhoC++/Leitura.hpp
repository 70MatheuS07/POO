#ifndef LEITURA_HPP
#define LEITURA_HPP

#include <string>
#include <iostream>
#include <limits>

class Leitura
{
public:
    static int LehInt(std::istream &input);
    static double LehDouble(std::istream &input);
    static std::string LehLine(std::istream &input);
};

#endif
