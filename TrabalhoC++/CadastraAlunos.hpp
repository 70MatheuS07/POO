#ifndef ALUNOMAPCADASTRARALUNOS_HPP
#define ALUNOMAPCADASTRARALUNOS_HPP

#include <string>
#include "CursoMap.hpp"
#include "AlunoGrad.hpp"
#include "AlunoPos.hpp"
#include "DisciplinaMap.hpp"
#include "AlunoMap.hpp"
#include "Excecao.hpp"

class CadastraAlunos{
public:
static void CadastrarAlunosFunction(AlunoMap *alunos, CursoMap *cursos, DisciplinaMap *disciplinas, const std::string &arquivo);
};

#endif 
