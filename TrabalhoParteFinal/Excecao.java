import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        int matricula;
        String Digitado;

        public NemGNemPException(int matricula, String digitado) {
            super("Tipo de aluno desconhecido para " + matricula + ": " + digitado + ".");
            this.matricula = matricula;
            this.Digitado = digitado;
        }
    }

    public static class CodCursoIndefinidoException extends Excecao{
        int matricula;
        int codigo;
        public CodCursoIndefinidoException(int matricula, int codigo) {
            super("Código de curso não definido usado no aluno " + matricula + ": " + codigo+".");
            this.matricula = matricula;
            this.codigo = codigo;
        }
    }

    public static class NemMNemDException extends Excecao {
        int matricula;
        String Digitado;

        public NemMNemDException(int  matricula, String digitado) {
            super("Tipo de aluno de pós-graduação desconhecido para " + matricula + ": " + digitado + ".");
            this.matricula = matricula;
            this.Digitado = digitado;
        }
    }

    public static class CodAvaliacaoIndefinidoException extends Excecao{
        ArrayList<Integer> M;
        String codigo;

        public CodAvaliacaoIndefinidoException(ArrayList<Integer> matriculas, String codigo){
            super("Códgo de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s) ");
            for(int i=0;i<matriculas.size();i++){
                System.out.println(matriculas.get(i));
                if(i==matriculas.size()-1){
                    System.out.println(" ");
                }
            }
            System.out.println(".");
            this.M=matriculas;
            this.codigo=codigo;
        }
    }

    public static class MatriculaIndefinidaException extends Excecao{
        String codigo;
        int matricula;

        public MatriculaIndefinidaException(String codigo, int matricula){
            super("Matricula de aluno não definida usada na planilha de notas, asssociada à avaliação "+codigo+":"+matricula+".");
        }
    }

    public static class NotaInvalidaAvaliacaoException extends Excecao{
        ArrayList<Integer> M;
        String codigo;
        int nota;
        public NotaInvalidaAvaliacaoException(ArrayList<Integer> matriculas, String codigo, int nota){
            super("Nota inválida para avaliação "+ codigo + "do(s) aluno(s)");
            for(int i=0;i<matriculas.size();i++){
                System.out.println(matriculas.get(i));
                if(i==matriculas.size()-1){
                    System.out.println(" ");
                }
            }
            System.out.println(": "+nota+".");
        }
    }

    public static class DisciplinaSemAvaliacaoException extends Excecao{
        String codigo;
        public DisciplinaSemAvaliacaoException(String codigo){
            super("A disciplina "+ codigo+ "não possui nenhuma avaliação cadastrada.");
        }
    }

    public static class AlunoNaoMatriculadoException extends Excecao{
        int matricula;
        String codigoA;
        String codigoD;

        public AlunoNaoMatriculadoException(int matricula, String codigoA, String codigoD){
            super("O aluno "+ matricula+ "não possui nota na avalialiação "+codigoA+ "da disciplina "+ codigoD+"porém não encontra-se matriculado nesta disciplina.");

    }
}

    public static class NotaDuplicada extends Excecao{
        int matricula;
        String codigo;
        public NotaDuplicada(int matricula, String codigo){
            super("O aluno "+ matricula + "foi registrado em mais de um grupo para a avaliação "+codigo+".");
        }
    } 

    public static class IOException extends Excecao {
        public IOException() {
            super("Erro de I/O.");
        }
    }
}