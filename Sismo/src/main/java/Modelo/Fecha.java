package Modelo;

import java.time.LocalDate;

public class Fecha implements Comparable<Fecha> {
    private final int dia;
    private final int mes;
    private final int anno;

    public Fecha(int dia, int mes, int anno) {
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mes debe estar entre 1 y 12");
        if (dia < 1 || dia > 31) throw new IllegalArgumentException("Día debe estar entre 1 y 31");
        if (anno < 0) throw new IllegalArgumentException("Año debe ser positivo");
        // Validación más precisa por mes/año es posible pero opcional.
        this.dia = dia;
        this.mes = mes;
        this.anno = anno;
    }

    public int getDia() { return dia; }
    public int getMes() { return mes; }
    public int getAnno() { return anno; }

    @Override
    public String toString() {
        // Puedes ajustar el formato si prefieres "dd-MM-yyyy" en vez de "yyyy-MM-dd"
        return String.format("%04d-%02d-%02d", anno, mes, dia);
    }

    // Métodos para interoperar con LocalDate
    public static Fecha fromLocalDate(LocalDate ld) {
        return new Fecha(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear());
    }

    public LocalDate toLocalDate() {
        return LocalDate.of(anno, mes, dia);
    }

    // Implementación de Comparable<Fecha>
    @Override
    public int compareTo(Fecha o) {
        if (this.anno != o.anno)
            return Integer.compare(this.anno, o.anno);
        if (this.mes != o.mes)
            return Integer.compare(this.mes, o.mes);
        return Integer.compare(this.dia, o.dia);
    }

    // Extra: método para crear Fecha desde un String tipo "yyyy-MM-dd" o "dd-MM-yyyy"
    public static Fecha fromString(String texto) {
        String[] partes = texto.split("-");
        if (partes[0].length() == 4) { // "yyyy-MM-dd"
            int anno = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);
            return new Fecha(dia, mes, anno);
        } else { // "dd-MM-yyyy"
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anno = Integer.parseInt(partes[2]);
            return new Fecha(dia, mes, anno);
        }
    }
}