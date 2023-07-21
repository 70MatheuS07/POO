CXX = g++
CXXFLAGS = -Wall -std=c++11

SRCS = main.cpp Aluno.cpp AlunoMap.cpp AlunoGrad.cpp AlunoPos.cpp Avaliacao.cpp AvaliacaoMap.cpp Curso.cpp CursoMap.cpp Disciplina.cpp DisciplinaMap.cpp Excecao.cpp Leitura.cpp Prova.cpp Trabalho.cpp Trim.cpp CriaAvaliacoesCSV.cpp CriaDisciplinasCSV.cpp CriaPautaDisciplinas.cpp CadastraAlunos.cpp CadastraAvaliacao.cpp
OBJS = $(SRCS:.cpp=.o)
EXEC = main

all: $(EXEC)

$(EXEC): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $@ $^

%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $<

run:
	./$(EXEC) -c cursos.csv -d disciplinas.csv -p avaliacoes.csv -a alunos.csv -n notas.csv

clean:
	rm -f $(OBJS) $(EXEC)