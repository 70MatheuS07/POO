#include "Excecao.hpp"

Excecao::Excecao(const std::string &msg) : std::runtime_error(msg) {}

FinalizaProgramaException::FinalizaProgramaException()
    : Excecao("") {}

ErroFormatacaoException::ErroFormatacaoException()
    : Excecao("Erro de formatação.") {}

CodigosIguaisException::CodigosIguaisException(int codigo)
    : Excecao("Código repetido para curso: " + std::to_string(codigo) + ".\n"),
      codigo(codigo) {}

MatriculasIguaisException::MatriculasIguaisException(int matricula)
    : Excecao("Matrícula repetida para aluno: " + std::to_string(matricula) + ".\n"),
      matricula(matricula) {}

CodDisciplinaIndefinidoAlunoExcpetion::CodDisciplinaIndefinidoAlunoExcpetion(int matricula, const std::string &codigo)
    : Excecao("Código de disciplina não definido usado no aluno " + std::to_string(matricula) + ": " + codigo + ".\n"),
      matricula(matricula),
      codigo(codigo) {}

CodDisciplinaIndefinidoAvalExcpetion::CodDisciplinaIndefinidoAvalExcpetion(const std::string &codigoA, const std::string &codigoD)
    : Excecao("Código de disciplina não definido usado na avaliação " + codigoA + ": " + codigoD + ".\n"),
      codigoA(codigoA),
      codigoD(codigoD) {}

PesoZeroNegativo::PesoZeroNegativo(const std::string &codigo, double peso)
    : Excecao("Peso de avaliação inválido para " + codigo + ": " + std::to_string((int)peso) + ".\n"),
      codigo(codigo),
      peso(peso) {}

NemPNemTException::NemPNemTException(const std::string &codigo, const std::string &digitado)
    : Excecao("Tipo de avaliação desconhecido para " + codigo + ": " + digitado + ".\n"),
      codigo(codigo),
      Digitado(digitado) {}

TamGrupoNaProvaException::TamGrupoNaProvaException(const std::string &codigo, int tam)
    : Excecao("Tamanho máximo de grupo especificado para a prova " + codigo + ": " + std::to_string(tam) + ".\n"),
      codigo(codigo),
      tam(tam) {}

TamMaxZeroNegativo::TamMaxZeroNegativo(const std::string &codigo, int peso)
    : Excecao("Tamanho máximo de grupo inválido para trabalho " + codigo + ": " + std::to_string(peso) + ".\n"),
      codigo(codigo),
      peso(peso) {}

NemGNemPException::NemGNemPException(int matricula, const std::string &digitado)
    : Excecao("Tipo de aluno desconhecido para " + std::to_string(matricula) + ": " + digitado + ".\n"),
      matricula(matricula),
      Digitado(digitado) {}

CodCursoIndefinidoException::CodCursoIndefinidoException(int matricula, int codigo)
    : Excecao("Código de curso não definido usado no aluno " + std::to_string(matricula) + ": " + std::to_string(codigo) + ".\n"),
      matricula(matricula),
      codigo(codigo) {}

NemMNemDException::NemMNemDException(int matricula, const std::string &digitado)
    : Excecao("Tipo de aluno de pós-graduação desconhecido para " + std::to_string(matricula) + ": " + digitado + ".\n"),
      matricula(matricula),
      Digitado(digitado) {}

CodAvaliacaoIndefinidoException::CodAvaliacaoIndefinidoException(const std::string &matriculas, const std::string &codigo)
    : Excecao("Código de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s) " + matriculas + ": " + codigo + ".\n"),
      Matriculas(matriculas),
      Codigo(codigo) {}

MatriculaIndefinidaException::MatriculaIndefinidaException(const std::string &codigo, int matricula)
    : Excecao("Matricula de aluno não definida usada na planilha de notas, asssociada à avaliação " + codigo + ":" + std::to_string(matricula) + ".\n"),
      codigo(codigo),
      matricula(matricula) {}

NotaInvalidaAvaliacaoException::NotaInvalidaAvaliacaoException(const std::string &matriculas, const std::string &codigo, const std::string &nota)
    : Excecao("Nota inválida para avaliação " + codigo + " do(s) aluno(s) " + matriculas + ": " + nota + ".\n"),
      M(matriculas),
      codigo(codigo),
      nota(nota) {}

DisciplinaSemAvaliacaoException::DisciplinaSemAvaliacaoException(const std::string &codigo)
    : Excecao("A disciplina " + codigo + " não possui nenhuma avaliação cadastrada.\n"),
      codigo(codigo) {}

AlunoNaoMatriculadoException::AlunoNaoMatriculadoException(int matricula, const std::string &codigoA, const std::string &codigoD)
    : Excecao("O aluno " + std::to_string(matricula) + " possui nota na avaliação " + codigoA + " da disciplina " + codigoD + ", porém não encontra-se matriculado nesta disciplina.\n"),
      matricula(matricula),
      codigoA(codigoA),
      codigoD(codigoD) {}

NotaDuplicada::NotaDuplicada(int matricula, const std::string &codigo)
    : Excecao("O aluno " + std::to_string(matricula) + " foi registrado em mais de um grupo para a avaliação " + codigo + ".\n"),
      matricula(matricula),
      codigo(codigo) {}

ErroDeIO::ErroDeIO() : Excecao("Erro de IO\n") {}