import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Excecao extends Exception {
    public Excecao(String msg) {
        super(msg);
    }

    public static class FinalizaProgramaException extends Excecao {
        public FinalizaProgramaException() {
            super("");
        }
    }

    public class ErroFormatacaoException extends Exception {
        public ErroFormatacaoException() {
            super("Erro de formatação.");
        }
    }

    public static class CodigosIguaisException extends Excecao {
        int codigo;

        public CodigosIguaisException(int codigo) {
            super("Código repetido para curso: " + codigo + ".");
            this.codigo = codigo;

        }

    }

    public static class MatriculasIguaisException extends Excecao {
        int matricula;

        public MatriculasIguaisException(int matricula) {
            super("Matrícula repetida para aluno: " + matricula + ".");
            this.matricula = matricula;

        }

    }

    public static class CodDisciplinaIndefinidoAlunoExcpetion extends Excecao {
        int matricula; // avaliacao ou aluno
        String codigo;

        public CodDisciplinaIndefinidoAlunoExcpetion(int matricula, String codigo) {
            super("código de didciplina não definido usado no aluno " + matricula + ": " + codigo);
            this.matricula = matricula;
            this.codigo = codigo;
        }
    }

    public static class CodDisciplinaIndefinidoAvalExcpetion extends Excecao {
        String codigoA; // avaliacao ou aluno
        String codigoD;

        public CodDisciplinaIndefinidoAvalExcpetion(String codigoA, String codigoD) {
            super("código de didciplina não definido usado na avaliação " + codigoA + ": " + codigoD);
            this.codigoA = codigoA;
            this.codigoD = codigoD;
        }
    }

    public static class PesoZeroNegativo extends Excecao {
        String codigo;
        double peso;

        public PesoZeroNegativo(String codigo, double peso) {
            super("Peso de avaliação inválido para " + codigo + ": " + peso);
            this.codigo = codigo;
            this.peso = peso;
        }
    }

    public static class NemPNemTException extends Excecao {
        String codigo;
        String Digitado;

        public NemPNemTException(String codigo, String digitado) {
            super("Tipo de avaliação desconhecido para " + codigo + ": " + digitado + ".");
            this.codigo = codigo;
            this.Digitado = digitado;
        }
    }

    public static class TamGrupoNaProvaException extends Excecao {
        String codigo;
        int tam;
        public TamGrupoNaProvaException(String codigo, int tam) {
            super("Tamanho máximo de grupo foi especificado a prova " + codigo +": " + tam);
        }
    }

    public static class TamMaxZeroNegativo extends Excecao {
        String codigo;
        double peso;

        public TamMaxZeroNegativo(String codigo, double peso) {
            super("Tamanho máximo de grupo invá1lido para trabalho " + codigo + ": " + peso);
            this.codigo = codigo;
            this.peso = peso;
        }
    }

    public static class NemGNemPException extends Excecao {
        String matricula;
        String Digitado;

        public NemGNemPException(String matricula, String digitado) {
            super("Tipo de aluno desconhecido para " + matricula + ": " + digitado + ".");
            this.matricula = matricula;
            this.Digitado = digitado;
        }
    }

    public static class CodCursoIndefinidoException extends Excecao{
        int matricula;
        int codigo;
        public CodCursoIndefinidoException(int matricula, int codigo) {
            super("Código de curso não definido usado no aluno " + matricula + ": " + codigo);
            this.matricula = matricula;
            this.codigo = codigo;
        }
    }

    public static class NemMNemDException extends Excecao {
        String matricula;
        String Digitado;

        public NemMNemDException(String matricula, String digitado) {
            super("Tipo de aluno de pós-graduação desconhecido para " + matricula + ": " + digitado + ".");
            this.matricula = matricula;
            this.Digitado = digitado;
        }
    }

    public static class CodAvaliacaoIndefinidoException extends Excecao{
        
    }

}
