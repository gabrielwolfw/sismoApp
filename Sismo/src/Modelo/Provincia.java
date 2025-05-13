/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mishe
 */

public enum Provincia {
    SAN_JOSE,
    ALAJUELA,
    CARTAGO,
    HEREDIA,
    GUANACASTE,
    PUNTARENAS,
    LIMON,
    SIN_ASIGNAR;  // opción por defecto si no se reporta

    @Override
    public String toString() {
        // Devuelve nombre con espacios y tildes si lo deseas
        switch(this) {
            case SAN_JOSE:    return "San José";
            case ALAJUELA:    return "Alajuela";
            case CARTAGO:     return "Cartago";
            case HEREDIA:     return "Heredia";
            case GUANACASTE:  return "Guanacaste";
            case PUNTARENAS:  return "Puntarenas";
            case LIMON:       return "Limón";
            case SIN_ASIGNAR: return "SIN ASIGNAR";
            default:          return name();
        }
    }
}

