package Registro;

import Modelo.*;
import java.time.LocalDate;
import java.util.*;

public class EstadisticasSismos {
    public static Map<Provincia, Integer> contarSismosPorProvincia(List<Sismo> sismos) {
        Map<Provincia, Integer> mapa = new HashMap<>();
        for (Sismo s : sismos) {
            mapa.put(s.getProvincia(), mapa.getOrDefault(s.getProvincia(), 0) + 1);
        }
        return mapa;
    }

    public static Map<Origen, Integer> contarSismosPorOrigen(List<Sismo> sismos) {
        Map<Origen, Integer> mapa = new HashMap<>();
        for (Sismo s : sismos) {
            mapa.put(s.getOrigen(), mapa.getOrDefault(s.getOrigen(), 0) + 1);
        }
        return mapa;
    }

    public static List<Sismo> filtrarSismosPorFecha(List<Sismo> sismos, LocalDate inicio, LocalDate fin) {
        List<Sismo> filtrados = new ArrayList<>();
        for (Sismo s : sismos) {
            if ((s.getFecha().isEqual(inicio) || s.getFecha().isAfter(inicio)) &&
                (s.getFecha().isEqual(fin) || s.getFecha().isBefore(fin))) {
                filtrados.add(s);
            }
        }
        return filtrados;
    }

    public static Map<Integer, Integer> contarSismosPorMes(List<Sismo> sismos, int year) {
        Map<Integer, Integer> mapa = new HashMap<>();
        for (Sismo s : sismos) {
            if (s.getFecha().getYear() == year) {
                int mes = s.getFecha().getMonthValue();
                mapa.put(mes, mapa.getOrDefault(mes, 0) + 1);
            }
        }
        return mapa;
    }

    public static String clasificacionPorMagnitud(double magnitud) {
        if (magnitud < 3.0) return "Micro";
        if (magnitud < 4.0) return "Menor";
        if (magnitud < 5.0) return "Ligero";
        if (magnitud < 6.0) return "Moderado";
        if (magnitud < 7.0) return "Fuerte";
        if (magnitud < 8.0) return "Mayor";
        return "Gran";
    }

    public static Map<String, Integer> contarPorClasificacionMagnitud(List<Sismo> sismos) {
        Map<String, Integer> mapa = new HashMap<>();
        for (Sismo s : sismos) {
            String clase = clasificacionPorMagnitud(s.getMagnitud());
            mapa.put(clase, mapa.getOrDefault(clase, 0) + 1);
        }
        return mapa;
    }
}