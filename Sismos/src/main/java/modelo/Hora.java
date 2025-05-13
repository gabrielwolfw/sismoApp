/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author isaac
 */
public class Hora {
    private int hora;
    private int min;
    private int seg;
    Hora(int h, int min, int seg){
        this.hora=h;
        this.min=min;
        this.seg=seg;
    }

    public int getHora() {
        return hora;
    }

    public int getMin() {
        return min;
    }

    public int getSeg() {
        return seg;
    }

    @Override
    public String toString() {
        return "Hora{" + "hora=" + hora + ", min=" + min + ", seg=" + seg + '}';
    }
    
}
