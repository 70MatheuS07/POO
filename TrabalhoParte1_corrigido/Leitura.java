import java.util.Scanner;

public class Leitura {
    public static int LehInt(Scanner scanner) {
        int inteiro = scanner.nextInt();
        scanner.nextLine();
        return inteiro;
    }

    public static double LehDouble(Scanner scanner) {
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public static String LehLine(Scanner scanner) {
        String linha = scanner.nextLine();
        return linha;
    }
}
