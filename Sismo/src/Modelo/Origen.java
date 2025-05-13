/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mishe
 */


public enum Origen {
    SUBDUCCION,
    CHOQUE_PLACAS,
    TECTONICO_FALLA_LOCAL,
    INTRAPLACA,
    DEFORMACION_INTERNA;

    @Override
    public String toString() {
        switch(this) {
            case SUBDUCCION:             return "Subducción";
            case CHOQUE_PLACAS:          return "Choque de placas";
            case TECTONICO_FALLA_LOCAL:  return "Tectónico por falla local";
            case INTRAPLACA:             return "Intra placa";
            case DEFORMACION_INTERNA:    return "Deformación interna";
            default:                     return name();
        }
    }
}
