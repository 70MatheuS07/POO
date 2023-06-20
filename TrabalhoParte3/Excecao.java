import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
public class Excecao extends Exception {
    public Excecao(String msg){
        super(msg);
    }
    public static class CodigosIguaisException extends Excecao{
        int codigo;

        public CodigosIguaisException(int codigo) {
            super("Código repetido para curso: "+ codigo +".");
            this.codigo = codigo;

        }
        
    }

    public static class MatriculasIguaisException extends Excecao{
        int matricula;

        public MatriculasIguaisException(int matricula) {
            super("Matrícula repetida para aluno: "+matricula+".");
            this.matricula = matricula;

        }

    }

    public static class PesoZeroNegativo extends Exception{
        String codigo;
        int peso;

        public PesoZeroNegativo(String codigo, int peso){
            super("Peso de avaliação inválido para "+ codigo+": "+ peso);
            this.codigo=codigo;
            this.peso=peso;
        }
    }

    
}
