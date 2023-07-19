package covid.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CovidService {

    private List<Covid> listaDeCasos = CarregarDadosExternos.carregarCasosCSV();

    private final EntradaDeDados leitor;
    private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
    private final String OPCAO_SAIR = "x";
    private final String OPCAO_LISTAR_POR_PERIODO = "1";
    private final String OPCAO_LISTAR_CASOS_POR_PERIODO = "2";
    private final String OPCAO_LISTAR_OBITOS_POR_PERIODO = "3";
    private final String OPCAO_LISTAR_CASOS = "4";
    private final String OPCAO_TIRAR_DADOS_DUPLICADOS = "5";


    public CovidService(EntradaDeDados leitor) {
        this.leitor = leitor;
        iniciaApp();
    }

    public void processar() {

        String opcaoDigitada = obterEntradaDoUsuario(leitor);

        while (!escolheuSair(opcaoDigitada)) {
            tratarOpcaoSelecionada(opcaoDigitada);
            opcaoDigitada = obterEntradaDoUsuario(leitor);
        }

        finalizaApp();

    }

    private void tratarOpcaoSelecionada(String opcaoDigitada) {
        switch (opcaoDigitada) {
            case OPCAO_SAIR:
                break;
            case OPCAO_LISTAR_POR_PERIODO:
                pularLinha(2);
                listarPorPeriodo();
                break;
            case OPCAO_LISTAR_CASOS_POR_PERIODO:
                pularLinha(2);
                listarCasosPeriodo();
                break;
            case OPCAO_LISTAR_OBITOS_POR_PERIODO:
                pularLinha(2);
                listarObitosDia();
                break;
            case OPCAO_LISTAR_CASOS:
                pularLinha(2);
                listarCasos();
                break;
            case OPCAO_TIRAR_DADOS_DUPLICADOS:
                pularLinha(2);
                tirarDadosDuplicados();
                System.out.println("DADOS DUPLICADOS EXCLUIDOS");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    private void listarPorPeriodo() {
        System.out.println("Digite as datas no formato dd/MM/yyyy.");
        String dataInicio = leitor.obterEntrada("Digite a data de início: ");
        String dataFim = leitor.obterEntrada("Digite a data de fim: ");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = dateFormat.parse(dataInicio);
            Date fim = dateFormat.parse(dataFim);

            List<Covid> casosNoPeriodo = new ArrayList<>();

            for (Covid caso : listaDeCasos) {
                if (caso.getData().compareTo(inicio) >= 0 && caso.getData().compareTo(fim) <= 0) {
                    casosNoPeriodo.add(caso);
                }
            }

            if (casosNoPeriodo.isEmpty()) {
                System.out.println("Não foram encontrados casos no período especificado.");
            } else {
                System.out.println("Casos no período de " + dataInicio + " a " + dataFim + ":");
                for (Covid caso : casosNoPeriodo) {
                    System.out.println(caso);
                }
            }
        } catch (ParseException e) {
            System.out.println("Data inválida. Certifique-se de digitar as datas no formato dd/MM/yyyy.");
        }
    }


    private void listarCasosPeriodo() {
        System.out.println("Digite as datas no formato dd/MM/yyyy.");
        String dataInicio = leitor.obterEntrada("Digite a data de início: ");
        String dataFim = leitor.obterEntrada("Digite a data de fim: ");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = dateFormat.parse(dataInicio);
            Date fim = dateFormat.parse(dataFim);

            Covid ultimoCaso = null;

            for (Covid caso : listaDeCasos) {
                if (caso.getData().compareTo(inicio) >= 0 && caso.getData().compareTo(fim) <= 0) {
                    ultimoCaso = caso;
                }
            }

            if (ultimoCaso == null) {
                System.out.println("Não foram encontrados casos no período especificado.");
            } else {
                System.out.println("O total de casos no período de " + dataInicio + " a " + dataFim + ":");
                System.out.println(ultimoCaso.getCasos());
            }
        } catch (ParseException e) {
            System.out.println("Data inválida. Certifique-se de digitar as datas no formato dd/MM/yyyy.");
        }
    }


    private void tirarDadosDuplicados() {
        Set<Covid> setCovid = new HashSet<>();
        setCovid.addAll(listaDeCasos);
        listaDeCasos.clear();
        listaDeCasos.addAll(setCovid);
    }

    public void pularLinha(int numeroDeLinhas) {
        for (int i = 1; i <= numeroDeLinhas; i++) {
            System.out.println();
        }
    }

    private void listarObitosDia() {
        System.out.println("Digite as datas no formato dd/MM/yyyy.");
        String dataInicio = leitor.obterEntrada("Digite a data de início: ");
        String dataFim = leitor.obterEntrada("Digite a data de fim: ");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = dateFormat.parse(dataInicio);
            Date fim = dateFormat.parse(dataFim);

            int somaObitos = 0;

            for (Covid caso : listaDeCasos) {
                if (caso.getData().compareTo(inicio) >= 0 && caso.getData().compareTo(fim) <= 0) {
                    somaObitos += caso.getObitosPorDia();
                }
            }

            System.out.println("A soma de óbitos no período de " + dataInicio + " a " + dataFim + " é: " + somaObitos);

        } catch (ParseException e) {
            System.out.println("Data inválida. Certifique-se de digitar as datas no formato dd/MM/yyyy.");
        }
    }


    private void listarCasos() {
        StringBuilder sb = new StringBuilder();

        if (listaDeCasos.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            for (Covid casos : listaDeCasos) {
                sb.append("\t").append(casos).append(",\n");
            }
            sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
            sb.append("\n]");
        }

        System.out.println(sb);
    }

    private boolean escolheuSair(String opcaoDigitada) {
        return opcaoDigitada.equals(OPCAO_SAIR);
    }

    private String obterEntradaDoUsuario(EntradaDeDados leitor) {
        carregaMenu();
        System.out.print(DIGITE_OPCAO_DESEJADA);
        return leitor.obterEntrada().toLowerCase();
    }

    private void finalizaApp() {
        System.out.println("Até logo!!");
    }

    private void opcaoInvalida() {
        System.out.println("Opção INVÁLIDA. Tente novamente.");
    }

    private void iniciaApp() {
        carregaNomeApp();
    }

    private void carregaMenu() {
        System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
        System.out.println("1 - LISTAR POR PERIODO");
        System.out.println("2 - LISTAR CASOS POR PERIODO");
        System.out.println("3 - LISTAR OBITOS POR PERIODO");
        System.out.println("4 - LISTAR CASOS");
        System.out.println("5 - LIMPAR DADOS DUPLICADOS");
        System.out.println("X - SAIR");
    }

    private void carregaNomeApp() {
        System.out.println("******************************************");
        System.out.println("******* COVID-19 EM SAO PAULO *********");
        System.out.println("******************************************");
    }

}

