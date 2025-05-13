package Registro;

import Modelo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelInteresadoManager {
    public static List<Interesado> cargarInteresados(String rutaArchivo) throws IOException {
        List<Interesado> interesados = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                String nombre = row.getCell(0).getStringCellValue().trim();
                String email = row.getCell(1).getStringCellValue().trim();
                String celular = row.getCell(2).getStringCellValue().trim();
                String provinciasStr = row.getCell(3).getStringCellValue().trim(); // por ejemplo: "SAN_JOSE;ALAJUELA"
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