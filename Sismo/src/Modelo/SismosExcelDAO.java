/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Sismo;

/**
 *
 * @author mishe
 */
public class SismosExcelDAO {
    private Path archivo = Paths.get("resources/sismos.xlsx");
    public List<Sismo> cargarTodos() { /* Apache POI: leer rows */ }
    public void guardar(Sismo s) { /* añadir row y escribir */ }
    public void actualizar(int fila, Sismo s) { /* modificar row */ }
}

