/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOAerolinea;
import DAO.DAOAeropuerto;
import Model.Aerolinea;
import Model.Aeropuerto;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Carlos
 */
public class CTRLAerolinea {
    DAOAerolinea dao;
    
    public CTRLAerolinea() {
        dao = new DAOAerolinea();
    }
    
    public void agregar(JTextField codigo, JTextField nombre, JTextField paisOrigen) {
        dao.agregarAerolinea(new Aerolinea(
            codigo.getText().toUpperCase(),
            nombre.getText(),
            paisOrigen.getText()
        ));
    }

    public void actualizar(int idAerolinea, JTextField codigo, JTextField nombre, JTextField paisOrigen) {
        Aerolinea aerolinea = new Aerolinea(
            codigo.getText().toUpperCase(),
            nombre.getText(),
            paisOrigen.getText()
        );
        aerolinea.setIdAerolinea(idAerolinea);
        dao.actualizarAerolinea(aerolinea);
    }

    public void eliminar(int idAerolinea) {
        dao.eliminarAerolinea(idAerolinea);
    }
    
    public void cargarComboBox(JComboBox<String> comboAerolineas) {
        comboAerolineas.removeAllItems();
        List<String> aerolineas = dao.llenarComboAerolineas();
        for (String aerolinea : aerolineas) {
            comboAerolineas.addItem(aerolinea);
        }
    }
    
    public void mostrarAerolineas(JTable tabla) {
        List<Aerolinea> listaAerolineas = dao.mostrarAerolineas();
        String[] columnas = {"ID", "Código", "Nombre", "País Origen"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Aerolinea aerolinea : listaAerolineas) {
            Object[] fila = {
                aerolinea.getIdAerolinea(),
                aerolinea.getCodigo(),
                aerolinea.getNombre(),
                aerolinea.getPaisOrigen()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
}
