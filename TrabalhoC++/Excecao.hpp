#ifndef EXCECAO_HPP
#define EXCECAO_HPP

#include <stdexcept>
#include <string>

class Excecao : public std::runtime_error
{
public:
    explicit Excecao(const std::string &msg);
};

class FinalizaProgramaException : public Excecao
{
public:
    FinalizaProgramaException();
};

class ErroFormatacaoException : public Excecao
{
public:
    ErroFormatacaoException();
};

class CodigosIguaisException : public Excecao
{
private:
    int codigo;

public:
    explicit CodigosIguaisException(int codigo);
};

class MatriculasIguaisException : public Excecao
{
private:
    int matricula;

public:
    explicit MatriculasIguaisException(int matricula);
};

class CodDisciplinaIndefinidoAlunoExcpetion : public Excecao
{
private:
    int matricula;
    std::string codigo;

public:
    CodDisciplinaIndefinidoAlunoExcpetion(int matricula, const std::string &codigo);
};

class CodDisciplinaIndefinidoAvalExcpetion : public Excecao
{
private:
    std::string codigoA;
    std::string codigoD;

public:
    CodDisciplinaIndefinidoAvalExcpetion(const std::string &codigoA, const std::string &codigoD);
};

class PesoZeroNegativo : public Excecao
{
private:
    std::string codigo;
    double peso;

public:
    PesoZeroNegativo(const std::string &codigo, double peso);
};

class NemPNemTException : public Excecao
{
private:
    std::string codigo;
    std::string Digitado;

public:
    NemPNemTException(const std::string &codigo, const std::string &digitado);
};

class TamGrupoNaProvaException : public Excecao
{
private:
    std::string codigo;
    int tam;

public:
    TamGrupoNaProvaException(const std::string &codigo, int tam);
};

class TamMaxZeroNegativo : public Excecao
{
private:
    std::string codigo;
    int peso;

public:
    TamMaxZeroNegativo(const std::string &codigo, int peso);
};

class NemGNemPException : public Excecao
{
private:
    int matricula;
    std::string Digitado;

public:
    NemGNemPException(int matricula, const std::string &digitado);
};

class CodCursoIndefinidoException : public Excecao
{
private:
    int matricula;
    int codigo;

public:
    CodCursoIndefinidoException(int matricula, int codigo);
};

class NemMNemDException : public Excecao
{
private:
    int matricula;
    std::string Digitado;

public:
    NemMNemDException(int matricula, const std::string &digitado);
};

class CodAvaliacaoIndefinidoException : public Excecao
{
private:
    std::string Matriculas;
    std::string Codigo;

public:
    CodAvaliacaoIndefinidoException(const std::string &matriculas, const std::string &codigo);
};

class MatriculaIndefinidaException : public Excecao
{
private:
    std::string codigo;
    int matricula;

public:
    MatriculaIndefinidaException(const std::string &codigo, int matricula);
};

class NotaInvalidaAvaliacaoException : public Excecao
{
private:
    std::string M;
    std::string codigo;
    std::string nota;

public:
    NotaInvalidaAvaliacaoException(const std::string &matriculas, const std::string &codigo, const std::string &nota);
};

class DisciplinaSemAvaliacaoException : public Excecao
{
private:
    std::string codigo;

public:
    DisciplinaSemAvaliacaoException(const std::string &codigo);
};

class AlunoNaoMatriculadoException : public Excecao
{
private:
    int matricula;
    std::string codigoA;
    std::string codigoD;

public:
    AlunoNaoMatriculadoException(int matricula, const std::string &codigoA, const std::string &codigoD);
};

class NotaDuplicada : public Excecao
{
private:
    int matricula;
    std::string codigo;

public:
    NotaDuplicada(int matricula, const std::string &codigo);
};

class ErroDeIO : public Excecao
{
public:
    ErroDeIO();
};

#endif
