package Modelo;

import java.time.LocalTime;

public class Hora implements Comparable<Hora> {
    private final int hora;
    private final int min;
    private final int seg;

    public Hora(int hora, int min, int seg) {
        if (hora < 0 || hora > 23) throw new IllegalArgumentException("Hora debe estar entre 0 y 23");
        if (min < 0 || min > 59) throw new IllegalArgumentException("Minuto debe estar entre 0 y 59");
        if (seg < 0 || seg > 59) throw new IllegalArgumentException("Segundo debe estar entre 0 y 59");
        this.hora = hora;
        this.min = min;
        this.seg = seg;
    }

    public int getHora() { return hora; }
    public int getMin() { return min; }
    public int getSeg() { return seg; }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hora, min, seg);
    }

    public static Hora fromLocalTime(LocalTime t) {
        return new Hora(t.getHour(), t.getMinute(), t.getSecond());
    }

    public LocalTime toLocalTime() {
        return LocalTime.of(hora, min, seg);
    }

    public static Hora fromString(String texto) {
        // Espera formato "hh:mm:ss"
        String[] partes = texto.split(":");
        if (partes.length < 2) throw new IllegalArgumentException("Formato de hora inválido: " + texto);
        int h = Integer.parseInt(partes[0]);
        int m = Integer.parseInt(partes[1]);
        int s = partes.length > 2 ? Integer.parseInt(partes[2]) : 0;
        return new Hora(h, m, s);
    }

    // Extra: método para comparar horas (opcional pero útil)
    @Override
    public int compareTo(Hora o) {
        if (this.hora != o.hora)
            return Integer.compare(this.hora, o.hora);
        if (this.min != o.min)
            return Integer.compare(this.min, o.min);
        return Integer.compare(this.seg, o.seg);
    }
}