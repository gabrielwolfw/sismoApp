package Registro;

/**
 *
 * @author lobok
 */

/**
 * Sismo provicional para el correo
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class Sismo {
    private LocalDate fecha;
    private LocalTime hora;
    private double profundidad;
    private String origen;
    private double magnitud;
    private String escala;
    private String descripcion;
    private String provincia;

    public Sismo(LocalDate fecha, LocalTime hora, double profundidad, String origen, double magnitud,
                 String escala, String descripcion, String provincia) {
        this.fecha = fecha;
        this.hora = hora;
        this.profundidad = profundidad;
        this.origen = origen;
        this.magnitud = magnitud;
        this.escala = escala;
        this.descripcion = descripcion;
        this.provincia = provincia;
    }

    // Getters y setters omitidos por brevedad

    @Override
    public String toString() {
        return "Sismo en " + provincia + " (" + descripcion + ") el " + fecha + " a las " + hora +
                " - Magnitud: " + magnitud + " " + escala;
    }
}
