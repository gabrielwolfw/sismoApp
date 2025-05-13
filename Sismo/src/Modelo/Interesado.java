/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Set;

public class Interesado {
    private String nombre;
    private String email;
    private String celular;
    private Set<Provincia> provinciasInteres;

    public Interesado(String nombre, String email, String celular, Set<Provincia> provinciasInteres) {
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.provinciasInteres = provinciasInteres;
    }

    public boolean leInteresa(Provincia provincia) {
        return provinciasInteres.contains(provincia);
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCelular() {
        return celular;
    }

    public Set<Provincia> getProvinciasInteres() {
        return provinciasInteres;
    }

    public void setProvinciasInteres(Set<Provincia> provinciasInteres) {
        this.provinciasInteres = provinciasInteres;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Interesado{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", provinciasInteres=" + provinciasInteres +
                '}';
    }
}
