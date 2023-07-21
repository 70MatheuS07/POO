#include "CriaPautaDisciplinas.hpp"

void CriaPautaDisciplinas::CriaPautaDisciplinasFunction(DisciplinaMap *disciplinas, AlunoMap *alunos, AvaliacaoMap *avaliacoes)
{
    try
    {
        double total = 0.0;
        double qtd_prov_trab = 0.0;

        for (auto &d : disciplinas->getDisciplinaMap())
        {
            std::string *key_d = const_cast<std::string *>(&d.first);
            Disciplina *value_d = &d.second;

            std::ofstream writer("1-pauta-" + *key_d + ".csv");
            writer << "Matrícula;Aluno;";

            auto *aluno_map = &(value_d->getAlunoMap().getAlunoMap());

            for (auto &entry_aluno : *aluno_map)
            {
                Aluno *value_a = &entry_aluno.second;

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
                    std::string &key_avaliacao_aluno = entry_ava.first;
                    double value_avaliacao_aluno = entry_ava.second;

                    auto *a = &(avaliacoes->getAvaliacaoMap().find(key_avaliacao_aluno)->second);

                    Avaliacao *aa = a;

                    if (aa->getDisciplinaKey().compare(*key_d) == 0)
                    {
                        // aa->WriteKeyAvaliacao(writer, aa, key_avaliacao_aluno);
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

                    auto *a = &(avaliacoes->getAvaliacaoMap().find(key_avaliacao_aluno)->second);

                    Avaliacao *aa = a;
                    /*
                                        if (aa->getDisciplinaKey().compare(*key_d) == 0)
                                        {
                                            Avaliacao::Valores_WriteValueAvaliacaoAluno valores = aa->WriteValueAvaliacaoAluno(writer, aa, value_avaliacao_aluno);

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
                                        */
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
