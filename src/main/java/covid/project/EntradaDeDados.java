package covid.project;

import java.util.Locale;
import java.util.Scanner;

public class EntradaDeDados implements AutoCloseable {

    private Scanner scanner;

    public EntradaDeDados(){
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
    }

    public String obterEntrada() {
        return obterEntrada("");
    }

    public String obterEntrada(String prompt) {
        if (!prompt.isEmpty()) {
            System.out.print(prompt);
        }
        return scanner.nextLine();
    }

    public Double obterEntradaAsDouble(){
        double retorno = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer
        return retorno;
    }

    public Integer obterEntradaAsInt(){
        Integer retorno = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        return retorno;
    }

    @Override
    public void close() {
        scanner.close();
    }
}
