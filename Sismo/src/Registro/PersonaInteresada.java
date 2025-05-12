/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Registro;


import java.util.HashSet;
import java.util.Set;

public class PersonaInteresada {
    private String nombre;
    private String email;
    private String celular;
    private Set<String> provinciasInteres;

    public PersonaInteresada(String nombre, String email, String celular, Set<String> provinciasInter) {
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.provinciasInter = provinciasInter;
    }

    public boolean leInteresa(String provincia) {
        return provinciasInter.contains(provincia);
    }

    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public String getCelular() { return celular; }

    // Getters/setters omitidos por brevedad
}