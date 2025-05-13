/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sismos.excel;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import modelo.*;
/**
 *
 * @author isaac
 */
public class EditorExcel {
    public EditorExcel(){
        
    }
    public void cargarExcel(Sismo sismo){
        Workbook workbook = new XSSFWorkbook(); // Archivo .xlsx
        Sheet hoja = workbook.createSheet("Datos");
        for(int i=0;i<4;i++){
            Row fila = hoja.createRow(i);
            for(int o=0;o<4;o++){
                Cell celda = fila.createCell(o);
                switch(o){
                    case 0:
                        celda.setCellValue(sismo.getFecha().toString());
                        break;
                    case 1:
                        celda.setCellValue(sismo.getHora().toString());
                        break;
                    case 2:
                        celda.setCellValue(sismo.getMagnitud());
                        break;
                    case 3:
                        celda.setCellValue(sismo.getEscala().toString());
                        break;
                }
                    
                }
            }try (FileOutputStream fileOut = new FileOutputStream("ejemplo.xlsx")) {
                workbook.write(fileOut);
                }catch (IOException e) {
                    e.printStackTrace();
                }  
        }
    }

