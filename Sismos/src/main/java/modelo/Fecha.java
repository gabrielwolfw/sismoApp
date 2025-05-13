/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author isaac
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anno;
    Fecha(int d, int m, int a){
        this.dia=d;
        this.mes=m;
        this.anno=a;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnno() {
        return anno;
    }
    
}
