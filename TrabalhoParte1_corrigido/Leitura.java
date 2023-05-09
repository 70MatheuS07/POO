import java.util.Scanner;

public class Leitura {
    public static int LehInteiro(Scanner scanner) {
        int codigo = scanner.nextInt();
        scanner.nextLine();
        return codigo;
    }

    public static double LehDouble(Scanner scanner) {
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    public static String LehString(Scanner scanner) {
        String valor = scanner.nextLine();
        return valor;
    }
}
