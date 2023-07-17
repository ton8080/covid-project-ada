package covid.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarregarDadosExternos {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/Dados-covid-19-estado - Dados-covid-19-estado.csv";
    private static final SimpleDateFormat DATE_FORMAT_WITH_MONTH = new SimpleDateFormat("dd/MMM/yyyy");
    private static final SimpleDateFormat DATE_FORMAT_WITHOUT_MONTH = new SimpleDateFormat("dd/MM/yyyy");

    public static List<Covid> carregarCasosCSV() {

        List<Covid> casos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {

            br.readLine(); // Remover o cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                Date data;

                if (dados[0].contains(".")) {
                    // Date format with abbreviated month name (dd/MMM/yyyy)
                    data = DATE_FORMAT_WITH_MONTH.parse(dados[0].trim());
                } else {
                    // Date format without abbreviated month name (dd/MM/yyyy)
                    data = DATE_FORMAT_WITHOUT_MONTH.parse(dados[0].trim());
                }

                Integer caso = Integer.parseInt(dados[1].trim());
                Integer casoPorDia = Integer.parseInt(dados[2].trim());

                Integer obitosPorDia = 0; // Default value when the field is empty
                if (dados.length > 3 && !dados[3].trim().isEmpty()) {
                    obitosPorDia = Integer.parseInt(dados[3].trim());
                }

                Covid casosDados = new Covid(data, caso, casoPorDia, obitosPorDia);
                casos.add(casosDados);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return casos;
    }
}
