package Registro;

import Modelo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelInteresadoManager {
    public static List<Interesado> cargarInteresados(String rutaArchivo) throws IOException {
        List<Interesado> interesados = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                String nombre = formatter.formatCellValue(row.getCell(0)).trim();
                String email = formatter.formatCellValue(row.getCell(1)).trim();
                String celular = formatter.formatCellValue(row.getCell(2)).trim();
                String provinciasStr = formatter.formatCellValue(row.getCell(3)).trim();
                Set<Provincia> provincias = new HashSet<>();
                for (String prov : provinciasStr.split(";")) {
                    if (!prov.isBlank()) {
                        provincias.add(Provincia.valueOf(prov.trim().toUpperCase()));
                    }
                }
                // Validaciones:
                if ((email.isEmpty() && celular.isEmpty()) || provincias.isEmpty()) {
                    System.err.println("Interesado inválido: " + nombre);
                    continue;
                }
                interesados.add(new Interesado(nombre, email, celular, provincias));
            }
        }
        return interesados;
    }
}