/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author mishe
 */
// modelo/Sismo.java


public class Sismo {
    private LocalDate fecha;        
    private LocalTime hora;      
    private double profundidadKm;  
    private Origen origen;     
    private double magnitud;       
    private String escala;         
    private double latitud;        
    private double longitud;   
    private Provincia provincia;  
    private String descripcionZona; 

    // Constructor vacío
    public Sismo() {
        this.provincia = Provincia.SIN_ASIGNAR;
    }

    // Getters y setters...

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public double getProfundidadKm() { return profundidadKm; }
    public void setProfundidadKm(double profundidadKm) { this.profundidadKm = profundidadKm; }

    public Origen getOrigen() { return origen; }
    public void setOrigen(Origen origen) { this.origen = origen; }

    public double getMagnitud() { return magnitud; }
    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
        this.escala = magnitud <= 6.9 ? "ML" : "MW";
    }

    public String getEscala() { return escala; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public Provincia getProvincia() { return provincia; }
    public void setProvincia(Provincia provincia) { this.provincia = provincia; }

    public String getDescripcionZona() { return descripcionZona; }
    public void setDescripcionZona(String descripcionZona) { this.descripcionZona = descripcionZona; }
}

