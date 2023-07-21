#include "CriaDisciplinasCSV.hpp"

void CriaDisciplinasCSV::CriaDisciplinasCSVFunction(DisciplinaMap *disciplinas, AlunoMap *alunos, AvaliacaoMap *avaliacoes, CursoMap *cursos)
{
    try
    {
        std::ofstream writer("2-disciplinas.csv");

        writer << "Código;Disciplina;Curso;Média;% Aprovados\n";

        // Ordena as disciplinas a serem impressas no arquivo.
        std::vector<std::pair<std::string, Disciplina>> entries2(disciplinas->getDisciplinaMap().begin(), disciplinas->getDisciplinaMap().end());
        std::sort(entries2.begin(), entries2.end(),
                  [](const std::pair<std::string, Disciplina> &a, const std::pair<std::string, Disciplina> &b)
                  {
                      return a.first < b.first;
                  });

        for (auto &d : entries2)
        {
            std::string &key_d = d.first;
            Disciplina *value_d = const_cast<Disciplina *>(&d.second);

            std::map<std::string, int> alunosGeral;
            std::map<std::string, double> mediaAlunos;
            std::map<std::string, int> alunosAprovados;

            for (auto &a : value_d->getAlunoMap().getAlunoMap())
            {
                Aluno *value_a = &a.second;

                // value_a->WriteAlunoGrad(value_a, cursos, alunosGeral, mediaAlunos, alunosAprovados, avaliacoes, key_d);
            }

            std::vector<std::pair<std::string, int>> entries(alunosGeral.begin(), alunosGeral.end());
            std::sort(entries.begin(), entries.end(),
                      [&mediaAlunos](const std::pair<std::string, int> &a, const std::pair<std::string, int> &b)
                      {
                          double mediaA = mediaAlunos[a.first] / a.second;
                          double mediaB = mediaAlunos[b.first] / b.second;
                          return mediaB < mediaA; // Inverte a ordem de comparação
                      });

            for (const auto &aluno_geral : entries)
            {
                const std::string &key_ag = aluno_geral.first;
                int value_ag = aluno_geral.second;

                writer << key_d << ";" << value_d->getNome() << ";" << key_ag << ";";

                double total = mediaAlunos[key_ag];
                double media = ((double)(total / value_ag));
                double aprovados = (((double)alunosAprovados[key_ag] / value_ag));
                std::stringstream ss;
                ss << std::fixed << std::setprecision(2) << media;
                std::string formattedMedia = ss.str();

                ss.str(""); // Clear the stream
                ss << std::fixed << std::setprecision(1) << (aprovados * 100);
                std::string formattedAprovados = ss.str();

                writer << formattedMedia << ";" << formattedAprovados << "\n";
            }
        }
    }
    catch (const std::exception &e)
    {
        throw ErroDeIO();
    }
}
