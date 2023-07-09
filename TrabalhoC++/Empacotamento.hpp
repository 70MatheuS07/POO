#ifndef EMPACOTAMENTO_HPP
#define EMPACOTAMENTO_HPP

#include <string>
#include "Dados.hpp"

void GravarArquivoBinario(const std::string& nomeArquivo, const Dados& dados);
Dados LerArquivoBinario(const std::string& nomeArquivo);

#endif
