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

    
}
