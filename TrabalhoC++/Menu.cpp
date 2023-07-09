#include <iostream>
#include <string>
#include <vector>
#include "Dados.hpp"

void realizarDesserializacao(Dados* dados, AlunoMap* alunos, CursoMap* cursos, DisciplinaMap* disciplinas, AvaliacaoMap* avaliacoes) {
    // O arquivo existe, então realizamos a desserialização
    // Dados dados = Empacotamento.LerArquivoBinario(Menu::dados);
    if (dados != nullptr) {
        // A desserialização foi bem-sucedida, utilize os objetos desserializados
        // conforme necessário
        // alunos = dados.getAlunos();
        // cursos = dados.getCursos();
        // disciplinas = dados.getDisciplinas();
        // avaliacoes = dados.getAvaliacoes();
    } else {
        std::cout << "Não foi possível realizar a desserialização do arquivo." << std::endl;
    }
}

int main(int argc, char* argv[]) {
    std::vector<std::string> args(argv, argv + argc);

    int i = 0;
    std::string nomeArquivo;

    Dados* dados = nullptr;
    AlunoMap* alunos = nullptr;
    CursoMap* cursos = nullptr;
    DisciplinaMap* disciplinas = nullptr;
    AvaliacaoMap* avaliacoes = nullptr;

    bool escreverDados = false;
    bool pulaSwitch = false;

    for (const std::string& p : args) {
        if (p == "--write-only") {
            realizarDesserializacao(dados, alunos, cursos, disciplinas, avaliacoes);
            pulaSwitch = true;
        } else if (p == "--read-only") {
            escreverDados = true;
            pulaSwitch = false;
        }
    }

    try {
        if (!pulaSwitch) {
            dados = new Dados();
            cursos = new CursoMap();
            disciplinas = new DisciplinaMap();
            alunos = new AlunoMap();
            avaliacoes = new AvaliacaoMap();

            for (i = 0; i < args.size(); i++) {
                if (args[i] == "-c") {
                    i++;
                    nomeArquivo = args[i];
                    cursos->CadastrarCursos(nomeArquivo);
                } else if (args[i] == "-d") {
                    i++;
                    nomeArquivo = args[i];
                    disciplinas->CadastrarDisciplinas(nomeArquivo);
                } else if (args[i] == "-a") {
                    i++;
                    nomeArquivo = args[i];
                    alunos->CadastrarAlunos(cursos, disciplinas, nomeArquivo);
                } else if (args[i] == "-p") {
                    i++;
                    nomeArquivo = args[i];
                    avaliacoes->CadastrarAvaliacoes(disciplinas, nomeArquivo);
                } else if (args[i] == "-n") {
                    i++;
                    nomeArquivo = args[i];
                    alunos->RegistraNotaAlunoAvaliacao(avaliacoes, disciplinas, nomeArquivo);
                }
            }
        }

        if (escreverDados) {
            // dados->setCursos(cursos);
            // dados->setDisciplinas(disciplinas);
            // dados->setAlunos(alunos);
            // dados->setAvaliacoes(avaliacoes);

            // Empacotamento.GravarArquivoBinario(Menu.dados, dados);
        } else {
            disciplinas->CriaPautaDisciplinas(alunos, avaliacoes);
            disciplinas->CriaDisciplinasCSV(alunos, avaliacoes, cursos);
            avaliacoes->CriaAvaliacoesCSV(disciplinas, alunos);
        }
    } catch (Excecao e) {
        std::cout << e.getMessage() << std::endl;
    } catch (std::invalid_argument& n) {
        std::cout << "Erro de formatação." << std::endl;
    }

    delete dados;
    delete alunos;
    delete cursos;
    delete disciplinas;
    delete avaliacoes;

    return 0;
}
