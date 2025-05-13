/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author isaac
 */
public class Localizacion {
    private float latitud;
    private float longitud;
    private String descripcion;
    private Provincia provincia;
    
    Localizacion(float lat, float lon, String des, Provincia p){
        this.latitud=lat;
        this.longitud=lon;
        this.descripcion=des;
        this.provincia=p;
    }

    @Override
    public String toString() {
        return "Localizacion{" + "latitud=" + latitud + ", longitud=" + longitud + ", descripcion=" + descripcion + ", provincia=" + provincia + '}';
    }
    
    public float getLatitud() {
        return latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Provincia getProvincia() {
        return provincia;
    }
    
}
