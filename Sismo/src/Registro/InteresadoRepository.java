/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Registro;


 
import java.io.*;
import java.util.*;

public class InteresadoRepository {
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
                Set<String> provincias = new HashSet<>(Arrays.asList(partes[3].split(";")));
                interesados.add(new Interesado(nombre, email, celular, provincias));
            }
        }
    }

    public List<Interesado> buscarPorProvincia(String provincia) {
        List<Interesado> result = new ArrayList<>();
        for (Interesado i : interesados) {
            if (i.leInteresa(provincia)) result.add(i);
        }
        return result;
    }

    public List<Interesado> getTodos() { return interesados; }
}