/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Main.java.Registro.RegistroSismoExcel;
import Modelo.Origen;
import Modelo.Provincia;
import Modelo.Sismo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;


/**
 *
 * @author mishe
 */

public class VentanaPrincipal extends javax.swing.JFrame {

    private RegistroSismoExcel regExcel;
    private JScrollPane        jScrollPaneTabla;
    private DefaultTableModel  tblModel;

    public VentanaPrincipal() {
        initComponents();

        // 1) Servicio Excel (ruta relativa al directorio de ejecución)
        regExcel = new RegistroSismoExcel("archivos/interesados.xlsx");

        // 2) Configuraciones de los spinners y combos
        configurarSpinnerHora();
        cargarProvincias();
        cargarOrigenes();

        // 3) Inicializar y vincular la JTable
        tblSismos = new JTable();
        jScrollPaneTabla = new JScrollPane();       // Si lo añadiste en GUI Builder, quita esta línea
        // Si ya tenías un JScrollPane en el designer llamado jScrollPaneTabla,
        // omite la creación nueva y usa directamente esa referencia.
        jScrollPaneTabla.setViewportView(tblSismos);
        // Si lo pusiste en el GUI Builder, asegúrate de que dentro de initComponents
        // ya está jScrollPaneTabla y elimina la línea anterior.

        // 4) Cargar datos iniciales
        cargarTabla();

        // 5) Listeners de botones
        btnGuardar.addActionListener(evt -> guardarSismo());
        btnBorrar .addActionListener(evt -> limpiarFormulario());
    }

    private void configurarSpinnerHora() {
        spinnerHora.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHora, "HH:mm:ss");
        spinnerHora.setEditor(editor);
    }

    private void cargarProvincias() {
        cmbProvincia.removeAllItems();
        for (Provincia p : Provincia.values()) {
            cmbProvincia.addItem(p.toString());
        }
        // seleccionar SIN ASIGNAR
        for (int i = 0; i < cmbProvincia.getItemCount(); i++) {
            if (cmbProvincia.getItemAt(i).equals(Provincia.SIN_ASIGNAR.toString())) {
                cmbProvincia.setSelectedIndex(i);
                break;
            }
        }
    }

    private void cargarOrigenes() {
        cmbOrigen.removeAllItems();
        for (Origen o : Origen.values()) {
            cmbOrigen.addItem(o.toString());
        }
        cmbOrigen.setSelectedIndex(0);
    }

    private void cargarTabla() {
        try {
            List<Sismo> lista = regExcel.cargarSismos();
            String[] columnas = {
                "Fecha","Hora","Profundidad","Origen",
                "Magnitud","Escala","Latitud","Longitud",
                "Descripción","Provincia","Zona"
            };
            tblModel = new DefaultTableModel(columnas, 0);
            for (Sismo s : lista) {
                tblModel.addRow(new Object[]{
                    s.getFecha(),
                    s.getHora(),
                    s.getProfundidad(),
                    s.getOrigen(),
                    s.getMagnitud(),
                    s.getEscala(),
                    s.getLatitud(),
                    s.getLongitud(),
                    s.getDescripcionZona(),
                    s.getProvincia(),
                    s.getZona()    // si Sismo tiene getZona()
                });
            }
            tblSismos.setModel(tblModel);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error cargando Excel:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarSismo() {
        try {
            Sismo s = new Sismo();

            Date fecha = jDateChooser1.getDate();
            s.setFecha(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            Date hora = (Date) spinnerHora.getValue();
            s.setHora(hora.toInstant().atZone(ZoneId.systemDefault()).toLocalTime());

            s.setProfundidad(Double.parseDouble(txtProfundidad.getText()));

            // parsear origen desde String
            String origenStr = (String) cmbOrigen.getSelectedItem();
            for (Origen o : Origen.values()) {
                if (o.toString().equals(origenStr)) {
                    s.setOrigen(o);
                    break;
                }
            }

            s.setMagnitud(Double.parseDouble(txtMagnitud.getText()));
            s.setLatitud(Double.parseDouble(txtLatitud.getText()));
            s.setLongitud(Double.parseDouble(txtLongitud.getText()));

            // parsear provincia desde String
            String provStr = (String) cmbProvincia.getSelectedItem();
            for (Provincia p : Provincia.values()) {
                if (p.toString().equals(provStr)) {
                    s.setProvincia(p);
                    break;
                }
            }

            s.setDescripcionZona(txtDescripcionZona.getText());

            // Persistir en el Excel y refrescar la tabla
            regExcel.agregarSismo(s);
            cargarTabla();
            limpiarFormulario();

            JOptionPane.showMessageDialog(this,
                "Sismo guardado:\n" +
                "Fecha: " + s.getFecha() + " " + s.getHora() + "\n" +
                "Magnitud: " + s.getMagnitud() + " " + s.getEscala()
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al guardar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarFormulario() {
        jDateChooser1.setDate(null);
        spinnerHora.setValue(new Date());
        txtProfundidad.setText("");
        cmbOrigen.setSelectedIndex(0);
        txtMagnitud.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        cmbProvincia.setSelectedIndex(0);
        txtDescripcionZona.setText("");
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() ->
            new VentanaPrincipal().setVisible(true)
        );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        txtMagnitud = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLongitud = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLatitud = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbOrigen = new javax.swing.JComboBox<>();
        cmbProvincia = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtProfundidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDescripcionZona = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        spinnerHora = new javax.swing.JSpinner();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSismos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/slide-1-36bbd258b7_2340x850.jpg"))); // NOI18N
        lblImagen.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setText("Red Sismológica Nacional");

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel1.setText("Reporte un sismo y ayude al OBVSICORI y a la RSN");

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel2.setText("Magnitud del Sismo:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        txtMagnitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMagnitudActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel4.setText("Fecha del Sismo:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel5.setText("Latitud");

        txtLatitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLatitudActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel6.setText("Longitud del Sismo:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel7.setText("Datos del Sismo - Reporte un sismo");

        cmbOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel8.setText("Provincia");

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel9.setText("Origen");

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel10.setText("Descripcion de la zona");

        txtProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProfundidadActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel11.setText("Profundidad");

        txtDescripcionZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionZonaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        jLabel12.setText("Hora");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(124, 124, 124)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(txtLatitud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcionZona, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(spinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMagnitud, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProfundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcionZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(40, 40, 40))
                            .addComponent(spinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtLatitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)))
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtMagnitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnBorrar))
                .addGap(49, 49, 49))
        );

        tblSismos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSismos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtMagnitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMagnitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMagnitudActionPerformed

    private void txtProfundidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfundidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProfundidadActionPerformed

    private void txtDescripcionZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionZonaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        jDateChooser1.setDate(null);
        spinnerHora.setValue(new Date());
        txtProfundidad.setText("");
        cmbOrigen.setSelectedIndex(0);
        txtMagnitud.setText("");
        txtLatitud.setText("");
        txtLongitud.setText("");
        cmbProvincia.setSelectedItem(Provincia.SIN_ASIGNAR);
        txtDescripcionZona.setText("");
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtLatitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLatitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLatitudActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbOrigen;
    private javax.swing.JComboBox<String> cmbProvincia;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JSpinner spinnerHora;
    private javax.swing.JTable tblSismos;
    private javax.swing.JTextField txtDescripcionZona;
    private javax.swing.JTextField txtLatitud;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtMagnitud;
    private javax.swing.JTextField txtProfundidad;
    // End of variables declaration//GEN-END:variables
}
