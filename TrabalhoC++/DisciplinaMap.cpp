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

        for (auto &d : disciplinas)
        {
            std::string *key_d = const_cast<std::string *>(&d.first);
            Disciplina *value_d = const_cast<Disciplina *>(&d.second);

            std::ofstream writer("1-pauta-" + *key_d + ".csv");
            writer << "Matrícula;Aluno;";

            auto *aluno_map = &(value_d->getAlunoMap().getAlunoMap());

            for (auto &entry_aluno : *aluno_map)
            {
                Aluno *value_a = const_cast<Aluno *>(&entry_aluno.second);

                auto avaliacoes_aluno = value_a->getNotasAvaliacoes();

                // Copia os elementos do std::map<std::string, double> para um vetor temporário.
                std::vector<std::pair<std::string, double>> temp_entries(avaliacoes_aluno.begin(), avaliacoes_aluno.end());

                // Ordena o vetor temporário pelas chaves (strings) do std::map.
                std::sort(temp_entries.begin(), temp_entries.end(),
                          [](const std::pair<std::string, double> &a, const std::pair<std::string, double> &b)
                          {
                              return a.first < b.first;
                          });

                // Agora você pode usar o vetor temp_entries conforme necessário.

                for (auto &entry_ava : temp_entries)
                {
                    auto *a = &(avaliacoes.getAvaliacaoMap().find(entry_ava.first)->second);

                    Avaliacao *aa = a;

                    if (aa->getDisciplinaKey() == *key_d)
                    {
                        aa->WriteKeyAvaliacao(writer, aa, entry_ava.first);
                    }
                }
                break;
            }

            writer << "Média Parcial;Prova Final;Média Final\n";

            // Copia os elementos do std::map<int, Aluno> para um vetor temporário.
            std::vector<std::pair<int, Aluno>> temp_entries(aluno_map->begin(), aluno_map->end());

            // Ordena o vetor temporário pelo nome do aluno.
            std::sort(temp_entries.begin(), temp_entries.end(),
                      [](const std::pair<int, Aluno> &a, const std::pair<int, Aluno> &b)
                      {
                          return a.second.getNome() < b.second.getNome();
                      });

            // Agora você pode usar o vetor temp_entries conforme necessário.

            for (auto &a : temp_entries)
            {
                int key_a = a.first;
                Aluno &value_a = a.second;
                total = 0.0;
                qtd_prov_trab = 0.0;
                double provaFinal = -1.0;

                writer << key_a << ";" << value_a.getNome() << ";";

                auto avaliacoesAluno = value_a.getNotasAvaliacoes();

                std::vector<std::pair<std::string, double>> entries2;
                for (auto &entry : avaliacoesAluno)
                {
                    entries2.push_back(entry); // Copia os elementos do mapa para o vetor
                }

                std::sort(entries2.begin(), entries2.end(),
                          [](const std::pair<std::string, double> &a, const std::pair<std::string, double> &b)
                          {
                              return a.first < b.first;
                          });

                for (auto &avaliacao_aluno : entries2)
                {
                    std::string &key_avaliacao_aluno = avaliacao_aluno.first;
                    double value_avaliacao_aluno = avaliacao_aluno.second;

                    auto *a = &avaliacoes.getAvaliacaoMap().find(key_avaliacao_aluno)->second;

                    Avaliacao *aa = a;

                    if (aa->getDisciplinaKey().compare(*key_d) == 0)
                    {
                        Avaliacao::Valores_WriteValueAvaliacaoAluno valores = aa->WriteValueAvaliacaoAluno(
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
        throw ErroDeIO();
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

                value_a->WriteAlunoGrad(value_a, cursos, alunosGeral, mediaAlunos, alunosAprovados, avaliacoes,
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

        writer.close();
    }
    catch (const std::ofstream::failure &e)
    {
        throw ErroDeIO();
    }
}