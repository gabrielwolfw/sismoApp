package Main.java.Registro;

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

    // Método para agregar un nuevo interesado al archivo Excel
    public static void agregarInteresado(String rutaArchivo, Interesado interesado) throws IOException {
        File file = new File(rutaArchivo);
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Interesados");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nombre");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Celular");
            header.createCell(3).setCellValue("Provincias");
        }

        int lastRow = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue(interesado.getNombre());
        row.createCell(1).setCellValue(interesado.getEmail());
        row.createCell(2).setCellValue(interesado.getCelular());
        // Provincias como string separado por ";"
        StringBuilder sb = new StringBuilder();
        for (Provincia p : interesado.getProvincias()) {
            if (sb.length() > 0) sb.append(";");
            sb.append(p.name());
        }
        row.createCell(3).setCellValue(sb.toString());

        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}