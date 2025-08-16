/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOAvion;
import Model.Avion;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Carlos
 */
public class CTRLAvion {
   DAOAvion dao;
    
    public CTRLAvion() {
        dao = new DAOAvion();
    }
    
    public void agregar(JTextField modelo, JTextField capacidad, JComboBox<String> aerolinea) {
        String aerolineaSeleccionada = (String) aerolinea.getSelectedItem();
        int idAerolinea = Integer.parseInt(aerolineaSeleccionada.split(" - ")[0]);
        
        dao.agregarAvion(new Avion(
            modelo.getText(),
            Integer.parseInt(capacidad.getText()),
            idAerolinea
        ));
    }

    public void actualizar(int idAvion, JTextField modelo, JTextField capacidad, JComboBox<String> aerolinea) {
        String aerolineaSeleccionada = (String) aerolinea.getSelectedItem();
        int idAerolinea = Integer.parseInt(aerolineaSeleccionada.split(" - ")[0]);
        
        Avion avion = new Avion(
            modelo.getText(),
            Integer.parseInt(capacidad.getText()),
            idAerolinea
        );
        avion.setIdAvion(idAvion);
        dao.actualizarAvion(avion);
    }
    

    public void eliminar(int idAvion) {
        dao.eliminarAvion(idAvion);
    }
    
    public void cargarComboBox(JComboBox<String> comboAviones) {
        comboAviones.removeAllItems();
        List<String> aviones = dao.llenarComboAviones();
        for (String avion : aviones) {
            comboAviones.addItem(avion);
        }
    }
    
    public void mostrarAviones(JTable tabla) {
        List<Avion> listaAviones = dao.mostrarAviones();
        String[] columnas = {"ID", "Modelo", "Capacidad", "ID Aerolínea"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Avion avion : listaAviones) {
            Object[] fila = {
                avion.getIdAvion(),
                avion.getModelo(),
                avion.getCapacidad(),
                avion.getIdAerolinea()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
}
