import java.io.Serializable;
import java.util.Objects;

public class Automovel implements Serializable {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double valor;

    public Automovel(String placa, String modelo, String marca, int ano, double valor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Placa: " + placa +
                ", Modelo: " + modelo +
                ", Marca: " + marca +
                ", Ano: " + ano +
                ", Valor: R$" + String.format("%.2f", valor);
    }

    public String toCSV() {
        return placa + "," + modelo + "," + marca + "," + ano + "," + valor;
    }

    // CSV
    public static Automovel fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 5) {
            try {
                String placa = parts[0];
                String modelo = parts[1];
                String marca = parts[2];
                int ano = Integer.parseInt(parts[3]);
                double valor = Double.parseDouble(parts[4]);
                return new Automovel(placa, modelo, marca, ano, valor);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter dados numéricos da linha CSV: " + csvLine);
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Automovel automovel = (Automovel) o;
        return Objects.equals(placa, automovel.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }
}
