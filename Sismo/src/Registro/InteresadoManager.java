package Registro;

import Modelo.*;
import java.io.*;
import java.util.*;

public class InteresadoManager {
    private List<Interesado> interesados = new ArrayList<>();

    public void cargarDesdeCSV(String rutaArchivo) throws IOException {
        interesados.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;
                String nombre = partes[0].trim();
                String email = partes[1].trim();
                String celular = partes[2].trim();
                Set<Provincia> provincias = new HashSet<>();
                for (String provStr : partes[3].split(";")) {
                    provStr = provStr.trim().toUpperCase().replace(" ", "_");
                    try {
                        provincias.add(Provincia.valueOf(provStr));
                    } catch (IllegalArgumentException e) {
                        // Puedes registrar un warning aquí si quieres
                    }
                }
                interesados.add(new Interesado(nombre, email, celular, provincias));
            }
        }
    }

    public List<Interesado> buscarPorProvincia(Provincia provincia) {
        List<Interesado> result = new ArrayList<>();
        for (Interesado i : interesados) {
            if (i.leInteresa(provincia)) result.add(i);
        }
        return result;
    }

    public List<Interesado> getTodos() { return interesados; }
}