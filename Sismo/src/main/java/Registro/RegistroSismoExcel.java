package Main.java.Registro;

import Modelo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class RegistroSismoExcel {
    private final String archivoExcel;

    public RegistroSismoExcel(String archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    // Leer todos los sismos del archivo Excel
    public List<Sismo> cargarSismos() throws IOException {
        List<Sismo> lista = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(archivoExcel);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet hoja = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = hoja.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // Saltar encabezado

            while (rowIterator.hasNext()) {
                Row fila = rowIterator.next();
                Fecha fecha = Fecha.fromString(obtenerTexto(fila.getCell(0)));
                Hora hora = Hora.fromString(obtenerTexto(fila.getCell(1)));
                double profundidad = fila.getCell(2).getNumericCellValue();
                Origen origen = Origen.valueOf(obtenerTexto(fila.getCell(3)));
                double magnitud = fila.getCell(4).getNumericCellValue();
                EscalaSismologica escala = EscalaSismologica.valueOf(obtenerTexto(fila.getCell(5)));
                double latitud = fila.getCell(6).getNumericCellValue();
                double longitud = fila.getCell(7).getNumericCellValue();
                String descripcionZona = obtenerTexto(fila.getCell(8));
                Provincia provincia = Provincia.valueOf(obtenerTexto(fila.getCell(9)));
                Zona zona = Zona.valueOf(obtenerTexto(fila.getCell(10)));

                lista.add(new Sismo(fecha, hora, profundidad, origen, magnitud, escala, latitud, longitud, descripcionZona, provincia, zona));
            }
        }
        return lista;
    }

    // Agregar un sismo al archivo Excel
    public void agregarSismo(Sismo sismo) throws IOException {
        File file = new File(archivoExcel);
        Workbook workbook;
        Sheet hoja;

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                hoja = workbook.getSheetAt(0);
            }
        } else {
            workbook = new XSSFWorkbook();
            hoja = workbook.createSheet("Sismos");
            // Encabezados
            Row header = hoja.createRow(0);
            String[] headers = {"Fecha", "Hora", "Profundidad", "Origen", "Magnitud", "Escala", "Latitud", "Longitud", "DescripcionZona", "Provincia", "Zona"};
            for (int i = 0; i < headers.length; i++) header.createCell(i).setCellValue(headers[i]);
        }

        int lastRow = hoja.getLastRowNum() + 1;
        Row fila = hoja.createRow(lastRow);

        fila.createCell(0).setCellValue(sismo.getFecha().toString());
        fila.createCell(1).setCellValue(sismo.getHora().toString());
        fila.createCell(2).setCellValue(sismo.getProfundidad());
        fila.createCell(3).setCellValue(sismo.getOrigen().name());
        fila.createCell(4).setCellValue(sismo.getMagnitud());
        fila.createCell(5).setCellValue(sismo.getEscala().name());
        fila.createCell(6).setCellValue(sismo.getLatitud());
        fila.createCell(7).setCellValue(sismo.getLongitud());
        fila.createCell(8).setCellValue(sismo.getDescripcionZona());
        fila.createCell(9).setCellValue(sismo.getProvincia().name());
        fila.createCell(10).setCellValue(sismo.getZona().name());

        try (FileOutputStream fos = new FileOutputStream(archivoExcel)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // Utilidad para obtener texto de una celda (numérica o string)
    private static String obtenerTexto(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
}