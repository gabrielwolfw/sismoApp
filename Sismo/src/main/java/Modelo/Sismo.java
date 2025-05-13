package Modelo;

public class Sismo {
    private Fecha fecha;
    private Hora hora;
    private double profundidad; // en kilómetros
    private Origen origen;
    private double magnitud;
    private EscalaSismologica escala;
    private double latitud;
    private double longitud;
    private String descripcionZona; // descripción detallada
    private Provincia provincia;    // puede ser SIN_ASIGNAR
    private Zona zona;

    public Sismo(Fecha fecha, Hora hora, double profundidad, Origen origen, double magnitud,
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

    // Getters
    public Fecha getFecha() { return fecha; }
    public Hora getHora() { return hora; }
    public double getProfundidad() { return profundidad; }
    public Origen getOrigen() { return origen; }
    public double getMagnitud() { return magnitud; }
    public EscalaSismologica getEscala() { return escala; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public String getDescripcionZona() { return descripcionZona; }
    public Provincia getProvincia() { return provincia; }
    public Zona getZona() { return zona; }

    // Setters
    public void setFecha(Fecha fecha) { this.fecha = fecha; }
    public void setHora(Hora hora) { this.hora = hora; }
    public void setProfundidad(double profundidad) { this.profundidad = profundidad; }
    public void setOrigen(Origen origen) { this.origen = origen; }
    public void setMagnitud(double magnitud) { this.magnitud = magnitud; }
    public void setEscala(EscalaSismologica escala) { this.escala = escala; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public void setDescripcionZona(String descripcionZona) { this.descripcionZona = descripcionZona; }
    public void setProvincia(Provincia provincia) { this.provincia = provincia; }
    public void setZona(Zona zona) { this.zona = zona; }

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