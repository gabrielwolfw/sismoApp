/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author isaac
 */
public class Sismo {
    private Fecha fecha;
    private Hora hora;
    private ORIGEN origen;
    private float magnitud;
    private int profundidad;
    private Localizacion localizacion;
    private ZONA zona;
    private ESCALA escala;
    
    Sismo(Fecha f, Hora h,ORIGEN o,float mag,Localizacion l,ZONA z, ESCALA e,int profundidad){
        this.fecha=f;
        this.hora=h;
        this.origen=o;
        this.magnitud=mag;
        this.localizacion=l;
        this.zona=z;
        this.escala=e;
        this.profundidad=profundidad;
    }
    
    @Override
    public String toString() {
        return "Sismo{" + "fecha=" + fecha + ", hora=" + hora + ", origen=" + origen + ", magnitud=" + magnitud + ", localizacion=" + localizacion + ", zona=" + zona + ", escala=" + escala + '}';
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public ORIGEN getOrigen() {
        return origen;
    }

    public void setOrigen(ORIGEN origen) {
        this.origen = origen;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public ZONA getZona() {
        return zona;
    }

    public void setZona(ZONA zona) {
        this.zona = zona;
    }

    public ESCALA getEscala() {
        return escala;
    }

    public void setEscala(ESCALA escala) {
        this.escala = escala;
    }
    
}
