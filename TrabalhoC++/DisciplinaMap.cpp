#include "DisciplinaMap.hpp"

std::map<std::string, Disciplina> &DisciplinaMap::getDisciplinaMap()
{
    return disciplinas;
}

void DisciplinaMap::CriaPautaDisciplinas(AlunoMap alunos, AvaliacaoMap avaliacoes)
{
    try
    {
        double total = 0.0;
        double qtd_prov_trab = 0.0;

        for (const auto &d : disciplinas)
        {
            const std::string &key_d = d.first;
            const Disciplina &value_d = d.second;

            std::ofstream writer("1-pauta-" + key_d + ".csv");
            writer << "Matrícula;Aluno;";

            const auto &aluno_map = value_d.getAlunoMap().getAlunoMap();

            for (const auto &entry_aluno : aluno_map)
            {
                const Aluno &value_a = entry_aluno.second;

                const auto &avaliacoes_aluno = value_a.getNotasAvaliacoes();

                std::vector<std::pair<std::string, double>> entries(avaliacoes_aluno.begin(), avaliacoes_aluno.end());
                std::sort(entries.begin(), entries.end(),
                          [](const std::pair<std::string, double> &a, const std::pair<std::string, double> &b)
                          {
                              return a.first < b.first;
                          });

                for (const auto &entry_ava : entries)
                {
                    const std::string &key_avaliacao_aluno = entry_ava.first;
                    const double value_avaliacao_aluno = entry_ava.second;

                    const Avaliacao &aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                    if (aa.getDisciplinaKey() == key_d)
                    {
                        aa.WriteKeyAvaliacao(writer, aa, key_avaliacao_aluno);
                    }
                }

                break;
            }

            writer << "Média Parcial;Prova Final;Média Final\n";

            std::vector<std::pair<int, Aluno>> entries(aluno_map.begin(), aluno_map.end());
            std::sort(entries.begin(), entries.end(),
                      [](const std::pair<int, Aluno> &a, const std::pair<int, Aluno> &b)
                      {
                          return a.second.getNome() < b.second.getNome();
                      });

            for (const auto &a : entries)
            {
                int key_a = a.first;
                const Aluno &value_a = a.second;
                total = 0.0;
                qtd_prov_trab = 0.0;
                double provaFinal = -1.0;

                writer << key_a << ";" << value_a.getNome() << ";";

                const auto &avaliacoesAluno = value_a.getNotasAvaliacoes();

                std::vector<std::pair<std::string, double>> entries2(avaliacoesAluno.begin(), avaliacoesAluno.end());
                std::sort(entries2.begin(), entries2.end(),
                          [](const std::pair<std::string, double> &a, const std::pair<std::string, double> &b)
                          {
                              return a.first < b.first;
                          });

                for (const auto &avaliacao_aluno : entries2)
                {
                    const std::string &key_avaliacao_aluno = avaliacao_aluno.first;
                    const double value_avaliacao_aluno = avaliacao_aluno.second;

                    const Avaliacao &aa = avaliacoes.getAvaliacaoMap().get(key_avaliacao_aluno);

                    if (aa.getDisciplinaKey() == key_d)
                    {
                        Avaliacao::Valores_WriteValueAvaliacaoAluno valores = aa.WriteValueAvaliacaoAluno(
                            writer, aa, value_avaliacao_aluno);

                        if (valores.getProva_final() == -1.0)
                        {
                            total += valores.getTotal();
                            qtd_prov_trab += valores.getQtd();
                        }
                        else
                        {
                            total += valores.getTotal();
                            qtd_prov_trab += valores.getQtd();
                            provaFinal = valores.getProva_final();
                        }
                    }
                }

                total = ((double)total / qtd_prov_trab);

                std::stringstream stream;
                stream << std::fixed << std::setprecision(2) << total;
                std::string formattedTotal = stream.str();

                writer << formattedTotal << ";";

                if (provaFinal >= 0.0)
                {
                    stream.str(""); // Clear the stream
                    stream << std::fixed << std::setprecision(2) << provaFinal;
                    formattedTotal = stream.str();
                    writer << formattedTotal << ";";
                    total = ((double)(total + provaFinal) / 2);

                    stream.str(""); // Clear the stream
                    stream << std::fixed << std::setprecision(2) << total;
                    formattedTotal = stream.str();
                    writer << formattedTotal;
                }
                else
                {
                    writer << "-;";
                    stream.str(""); // Clear the stream
                    stream << std::fixed << std::setprecision(2) << total;
                    formattedTotal = stream.str();
                    writer << formattedTotal;
                }

                writer << "\n";
            }
            writer.close();
        }
    }
    catch (const std::ofstream::failure &e)
    {
        throw Excecao::ErroDeIO();
    }
}

void DisciplinaMap::CriaDisciplinasCSV(AlunoMap alunos, AvaliacaoMap avaliacoes, CursoMap cursos)
{
    try
    {
        std::ofstream writer("2-disciplinas.csv");
        writer << "Código;Disciplina;Curso;Média;% Aprovados\n";

        // Ordena as disciplinas a serem impressas no arquivo.
        std::vector<std::pair<std::string, Disciplina>> entries2(disciplinas.begin(), disciplinas.end());
        std::sort(entries2.begin(), entries2.end(),
                  [](const std::pair<std::string, Disciplina> &a, const std::pair<std::string, Disciplina> &b)
                  {
                      return a.first < b.first;
                  });

        for (const auto &d : entries2)
        {
            const std::string &key_d = d.first;
            const Disciplina &value_d = d.second;

            std::map<std::string, int> alunosGeral;
            std::map<std::string, double> mediaAlunos;
            std::map<std::string, int> alunosAprovados;

            for (const auto &a : value_d.getAlunoMap().getAlunoMap())
            {
                const Aluno &value_a = a.second;

                value_a.WriteAlunoGrad(value_a, cursos, alunosGeral, mediaAlunos, alunosAprovados, avaliacoes,
                                       key_d);
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

                writer << key_d << ";" << value_d.getNome() << ";" << key_ag << ";";

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

        writer.close();
    }
    catch (const std::ofstream::failure &e)
    {
        throw Excecao::ErroDeIO();
    }
}