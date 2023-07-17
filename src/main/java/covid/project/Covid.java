package covid.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Covid {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private Date data;
    private Integer casos;
    private Integer casosPorDia;
    private Integer obitosPorDia;

    public Covid(Date data, Integer casos, Integer casosPorDia, Integer obitosPorDia) {
        this.data = data;
        this.casos = casos;
        this.casosPorDia = casosPorDia;
        this.obitosPorDia = obitosPorDia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getCasos() {
        return casos;
    }

    public void setCasos(Integer casos) {
        this.casos = casos;
    }

    public Integer getCasosPorDia() {
        return casosPorDia;
    }

    public void setCasosPorDia(Integer casosPorDia) {
        this.casosPorDia = casosPorDia;
    }

    public Integer getObitosPorDia() {
        return obitosPorDia;
    }

    public void setObitosPorDia(Integer obitosPorDia) {
        this.obitosPorDia = obitosPorDia;
    }

    @Override
    public String toString() {
        return "Covid{" +
                "data=" + DATE_FORMAT.format(data) +
                ", casos=" + casos +
                ", casosPorDia=" + casosPorDia +
                ", obitosPorDia=" + obitosPorDia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Covid covid = (Covid) o;
        return Objects.equals(data, covid.data) &&
                Objects.equals(casos, covid.casos) &&
                Objects.equals(casosPorDia, covid.casosPorDia) &&
                Objects.equals(obitosPorDia, covid.obitosPorDia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, casos, casosPorDia, obitosPorDia);
    }
}
