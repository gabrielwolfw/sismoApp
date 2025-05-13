/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import com.mycompany.sismos.excel.EditorExcel;


/**
 *
 * @author isaac
 */
public class Sismos {

    public static void main(String[] args) {
        EditorExcel editor= new EditorExcel();
        Localizacion l=new Localizacion(1,1,"a",Provincia.SAN_JOSE);
        Fecha f=new Fecha(1,1,1);
        Hora h=new Hora(1,1,1);
        Sismo sismo=new Sismo(f,h,ORIGEN.CHOQUE_PLACAS, 2,l,ZONA.MARITIMA,ESCALA.ML,4);
        editor.cargarExcel(sismo);
    }
}
