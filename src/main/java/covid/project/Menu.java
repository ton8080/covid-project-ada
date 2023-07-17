package covid.project;

import java.util.LinkedList;
import java.util.List;

public class Menu {

    private List<Covid> listaDeCasos = new LinkedList<>();

    private final EntradaDeDados leitor;
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_ATUALIZAR_DADOS = "1";
    private final String OPCAO_LISTAR_CASOS_POR_DIA = "2";
    private final String OPCAO_LISTAR_OBITOS_POR_DIA = "3";
    private final String OPCAO_LISTAR_CASOS = "4";

    public Menu(EntradaDeDados leitor){
        this.leitor = leitor;
        iniciaApp();
    }

    public void processar(){

        String opcaoDigitada = obterEntradaDoUsuario(leitor);

        while(!escolheuSair(opcaoDigitada)){
            tratarOpcaoSelecionada(opcaoDigitada);
            opcaoDigitada = obterEntradaDoUsuario(leitor);
        }

        finalizaApp();

    }

    private void tratarOpcaoSelecionada(String opcaoDigitada) {
        switch (opcaoDigitada){
            case OPCAO_SAIR:
                break;
            case OPCAO_ATUALIZAR_DADOS:
                System.out.println("Cadastro realizado com sucesso!");
                pularLinha(2);
                break;
            case OPCAO_LISTAR_CASOS_POR_DIA:
                pularLinha(2);
                break;
            case OPCAO_LISTAR_OBITOS_POR_DIA:
                break;
            case OPCAO_LISTAR_CASOS:
                listarCasos();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    public void pularLinha(int numeroDeLinhas){
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }

    private void listarCasos(){
        StringBuilder sb = new StringBuilder();

        listaDeCasos = CarregarDadosExternos.carregarCasosCSV();

        if (listaDeCasos.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (Covid casos: listaDeCasos) {
                sb.append("\t").append(casos).append(",\n");
            }
            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
            sb.append("\n]");
        }

        System.out.println(sb);
    }

    private boolean escolheuSair(String opcaoDigitada){
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor){
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }

    private void finalizaApp(){
        System.out.println("Até logo!!");
    }

    private void opcaoInvalida(){
        System.out.println("Opção INVÁLIDA. Tente novamente.");
    }

    private void iniciaApp(){
        carregaNomeApp();
    }

    private void carregaMenu() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
        System.out.println("1 - ATUALIZAR DADOS");
        System.out.println("2 - LISTAR CASOS POR DIA)");
        System.out.println("3 - LISTAR OBITOS POR DIA");
        System.out.println("4 - LISTAR CASOS");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp(){
        System.out.println("******************************************");
        System.out.println("******* COVID-19 EM SAO PAULO *********");
        System.out.println("******************************************");
    }

}

