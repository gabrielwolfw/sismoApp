/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sismo {
    private LocalDate fecha;
    private LocalTime hora;
    private double profundidad; // en kilómetros
    private Origen origen;
    private double magnitud;
    private EscalaSismologica escala;
    private double latitud;
    private double longitud;
    private String descripcionZona; // descripción detallada
    private Provincia provincia;    // puede ser SIN_ASIGNAR
    private Zona zona;

    public Sismo(LocalDate fecha, LocalTime hora, double profundidad, Origen origen, double magnitud,
                 EscalaSismologica escala, double latitud, double longitud, String descripcionZona,
                 Provincia provincia, Zona zona) {
        this.fecha = fecha;
        this.hora = hora;
        this.profundidad = profundidad;
        this.origen = origen;
        this.magnitud = magnitud;
        this.escala = escala;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionZona = descripcionZona;
        this.provincia = provincia;
        this.zona = zona;
    }

    // Getters y setters omitidos por brevedad

    @Override
    public String toString() {
        return "Sismo{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", profundidad=" + profundidad +
                ", origen=" + origen +
                ", magnitud=" + magnitud +
                ", escala=" + escala +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", descripcionZona='" + descripcionZona + '\'' +
                ", provincia=" + provincia +
                ", zona=" + zona +
                '}';
    }
}