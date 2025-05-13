/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sismos.excel;
import java.io.*;
import java.util.ArrayList;
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
    public void cargarExcel(ArrayList<Sismo> listaSismo){
        Workbook workbook = new XSSFWorkbook(); // Archivo .xlsx
        Sheet hoja = workbook.createSheet("Datos");
        int i=0;
        
            while(i<listaSismo.size()){
                for(Sismo sismo:listaSismo){
                    Row fila = hoja.createRow(i);
                
                    for(int o=0;o<7;o++){
                    Cell celda = fila.createCell(o);
                
                        switch(o){
                        case 0:
                         celda.setCellValue(sismo.getFecha().toString());
                           break;
                        case 1:
                          celda.setCellValue(sismo.getHora().toString());
                             break;
                        case 2:
                            celda.setCellValue("Profundidad: "+sismo.getProfundidad());
                            break;
                        case 3:
                            celda.setCellValue(sismo.getEscala().toString());
                            break;
                        case 4:
                            celda.setCellValue(sismo.getOrigen().toString());
                            break;
                        case 5:
                            celda.setCellValue("Magnitud: "+sismo.getMagnitud());
                            break;
                        case 6:
                            celda.setCellValue(sismo.getLocalizacion().toString());
                            break;
                    }  
                }
            i++;}try (FileOutputStream fileOut = new FileOutputStream("InfoSismos.xlsx")) {
                workbook.write(fileOut);
                }catch (IOException e) {
                    e.printStackTrace();
                }  }
        }
    }

