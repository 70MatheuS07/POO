#include "CriaAvaliacoesCSV.hpp"

void CriaAvaliacoesCSV::CriaAvaliacoesCSVFunction(AvaliacaoMap *avaliacoes, DisciplinaMap *disciplinas, AlunoMap *alunos)
{
    try
    {
        std::ofstream writer("3-avaliacoes.csv");

        std::vector<std::pair<std::string, Avaliacao>> entries(avaliacoes->getAvaliacaoMap().begin(), avaliacoes->getAvaliacaoMap().end());
        std::sort(entries.begin(), entries.end(), [](const std::pair<const std::string, Avaliacao> &a, const std::pair<const std::string, Avaliacao> &b)
                  {
    if (a.second.getDisciplinaKey() == b.second.getDisciplinaKey()) {
        return a.first < b.first;
    }
    return a.second.getDisciplinaKey() < b.second.getDisciplinaKey(); });

        std::map<std::string, int> qtdNotas;
        std::map<std::string, double> totalNotas;
        std::map<std::string, int> qtdNotasIO;
        std::map<std::string, double> totalNotasIO;

        writer << "Disciplina;Código;Avaliação;Data;Média\n";

        for (const auto &a : entries)
        {
            const std::string &key_a = a.first;

            for (const auto &aluno : alunos->getAlunoMap())
            {
                const Aluno &value_aluno = aluno.second;

                for (const auto &na : value_aluno.getNotasAvaliacoes())
                {
                    const std::string &key_NA = na.first;
                    double value_NA = na.second;

                    if (key_a == key_NA)
                    {
                        auto it = avaliacoes->getAvaliacaoMap().find(key_NA);
                        if (it != avaliacoes->getAvaliacaoMap().end())
                        {
                            Avaliacao &avaliacao = it->second;
                        }

                        // avaliacao.ModificaMapasNotas(qtdNotas, totalNotas, avaliacao, key_a, value_NA);
                    }
                }
            }
        }

        std::vector<std::pair<std::string, double>> entries2(totalNotas.begin(), totalNotas.end());
        std::sort(entries2.begin(), entries2.end(), [&](const std::pair<const std::string, double> &a, const std::pair<const std::string, double> &b)
                  {
    if (avaliacoes->getAvaliacaoMap().find(a.first)->first == avaliacoes->getAvaliacaoMap().find(b.first)->first) {
        return a.first < b.first;
    }
    return avaliacoes->getAvaliacaoMap().find(a.first)->first < avaliacoes->getAvaliacaoMap().find(b.first)->first; });

        for (const auto &entry : entries2)
        {
            const std::string &key_entry = entry.first;

            if (totalNotasIO.count(key_entry))
            {
                std::string codigo = key_entry;
                double media = static_cast<double>(totalNotasIO[key_entry]) / qtdNotasIO[key_entry];

                std::stringstream ss;
                ss << std::fixed << std::setprecision(2) << media;
                std::string formattedMedia = ss.str();

                auto it = avaliacoes->getAvaliacaoMap().find(key_entry);
                if (it != avaliacoes->getAvaliacaoMap().end())
                {
                    Avaliacao &avaliacao = it->second;

                    std::string disciplinaAvaliacao = avaliacao.getDisciplinaKey();
                    std::string nomeAvaliacao = avaliacao.getNome();

                    std::time_t dataAvaliacao = avaliacao.getData();

                    std::tm tm_data = *std::localtime(&dataAvaliacao);
                    char buffer[11];
                    std::strftime(buffer, sizeof(buffer), "%d/%m/%Y", &tm_data);

                    writer << disciplinaAvaliacao << ";";
                    writer << codigo << ";";
                    writer << nomeAvaliacao << ";";
                    writer << buffer << ";";
                    writer << formattedMedia << "\n";
                }
            }
        }
    }
    catch (std::ofstream::failure &e)
    {
        throw ErroDeIO();
    }
}
