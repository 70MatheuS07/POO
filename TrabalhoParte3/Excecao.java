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

    public static class CodDisciplinaIndefinidoExcpetion extends Excecao{
        Object objeto; //avaliacao ou aluno

        public CodDisciplinaIndefinidoExcpetion(Object objeto) {
            if(objeto instanceof Aluno){
                Aluno aluno=(Aluno)objeto;
                super("código de didciplina não definido usado no aluno "+ aluno.getNome()+": " aluno.get)
            }
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

    public static class NemMNemDException extends Excecao {
        String matricula;
        String Digitado;

        public NemMNemDException(String matricula, String digitado) {
            super("Tipo de aluno de pós-graduação desconhecido para " + matricula + ": " + digitado + ".");
            this.matricula = matricula;
            this.Digitado = digitado;
        }
    }



}
