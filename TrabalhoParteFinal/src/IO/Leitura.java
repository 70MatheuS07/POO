package src.IO;
import java.util.Scanner;

public class Leitura {
    /**
     * Lê um tipo int evitando a quebra de linha.
     * 
     * @param scanner
     * @return tipo int lido.
     */
    public static int LehInt(Scanner scanner) {
        int inteiro = scanner.nextInt();
        scanner.nextLine();
        return inteiro;
    }

    /**
     * Lê um tipo double evitando a quebra de linha.
     * 
     * @param scanner
     * @return tipo double lido.
     */
    public static double LehDouble(Scanner scanner) {
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    /**
     * Lê um tipo String.
     * 
     * @param scanner
     * @return tipo String lido.
     */
    public static String LehLine(Scanner scanner) {
        String linha = scanner.nextLine();
        return linha;
    }
}
