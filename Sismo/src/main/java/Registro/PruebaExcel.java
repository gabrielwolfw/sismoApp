package Registro;

import Modelo.*;
import java.util.List;

public class PruebaExcel {
    public static void main(String[] args) {
        try {
            List<Interesado> interesados = ExcelInteresadoManager.cargarInteresados("archivos/interesados.xlsx");
            for (Interesado i : interesados) {
                System.out.println(i.getNombre() + " | " + i.getEmail() + " | " + i.getCelular() + " | " + i.getProvinciasInteres());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
