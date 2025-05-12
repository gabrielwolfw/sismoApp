/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Localizacion {
    private double latitud;
    private double longitud;
    private String descripcionZona;
    private Provincia provincia;

    public Localizacion(double latitud, double longitud, String descripcionZona, Provincia provincia) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionZona = descripcionZona;
        this.provincia = provincia;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDescripcionZona() {
        return descripcionZona;
    }

    public void setDescripcionZona(String descripcionZona) {
        this.descripcionZona = descripcionZona;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                ", descripcionZona='" + descripcionZona + '\'' +
                ", provincia=" + provincia +
                '}';
    }
}