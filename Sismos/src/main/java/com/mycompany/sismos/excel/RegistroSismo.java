/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sismos.excel;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author isaac
 */
public class RegistroSismo {
    private ArrayList<Sismo> listaSismo;
    
    public RegistroSismo(){
        this.listaSismo=new ArrayList<Sismo>();
    }
    
    public void AgregarSismo(Sismo s){
        listaSismo.add(s);
    }
    
    public ArrayList<Sismo> getlistaSismo(){
        return this.listaSismo;
    }
}
