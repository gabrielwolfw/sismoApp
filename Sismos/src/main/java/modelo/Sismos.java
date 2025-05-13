/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import com.mycompany.sismos.excel.*;


/**
 *
 * @author isaac
 */
public class Sismos {

    public static void main(String[] args) {
        EditorExcel editor= new EditorExcel();
        RegistroSismo rs=new RegistroSismo();
        Localizacion l=new Localizacion(1,1,"a",Provincia.SAN_JOSE);
        Fecha f=new Fecha(1,1,1);
        Hora h=new Hora(1,1,1);
        Sismo sismo=new Sismo(f,h,ORIGEN.CHOQUE_PLACAS, 2,l,ZONA.MARITIMA,ESCALA.ML,4);
        Sismo sismo2=new Sismo(f,h,ORIGEN.DEFORMACION_INTERNA,2,l,ZONA.TERRESTRE,ESCALA.MW,5);
        Sismo sismo4=new Sismo(f,h,ORIGEN.INTRA_PLACA,3,l,ZONA.MARITIMA,ESCALA.MW,9);
        Sismo sismo3=new Sismo(f,h,ORIGEN.SUBDUCCION,6,l,ZONA.TERRESTRE,ESCALA.ML,7);
        rs.AgregarSismo(sismo);
        rs.AgregarSismo(sismo2);
        rs.AgregarSismo(sismo3);
        rs.AgregarSismo(sismo4);
        editor.cargarExcel(rs.getlistaSismo());
    }
}
