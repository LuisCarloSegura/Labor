/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOAeropuerto;
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
public class CTRLAeropuerto {
    DAOAeropuerto dao;
    
    public CTRLAeropuerto() {
        dao = new DAOAeropuerto();
    }
    
    public void agregar(JTextField codigoIata, JTextField nombre, JTextField ciudad, JTextField pais) {
        dao.agregarAeropuerto(new Aeropuerto(
            codigoIata.getText().toUpperCase(),
            nombre.getText(),
            ciudad.getText(),
            pais.getText()
        ));
    }
    
    public void actualizar(int idAeropuerto, JTextField codigoIata, JTextField nombre, JTextField ciudad, JTextField pais) {
        Aeropuerto aeropuerto = new Aeropuerto(
            codigoIata.getText().toUpperCase(),
            nombre.getText(),
            ciudad.getText(),
            pais.getText()
        );
        aeropuerto.setIdAeropuerto(idAeropuerto);
        dao.actualizarAeropuerto(aeropuerto);
    }
    
    public void eliminar(int idAeropuerto) {
        dao.eliminarAeropuerto(idAeropuerto);
    }
    
    public void cargarComboBox(JComboBox<String> comboAeropuertos) {
        comboAeropuertos.removeAllItems();
        List<String> aeropuertos = dao.llenarComboAeropuertos();
        for (String aeropuerto : aeropuertos) {
            comboAeropuertos.addItem(aeropuerto);
        }
    }
    
    public void mostrarAeropuertos(JTable tabla) {
        List<Aeropuerto> listaAeropuertos = dao.mostrarAeropuertos();
        String[] columnas = {"ID", "Código IATA", "Nombre", "Ciudad", "País"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Aeropuerto aeropuerto : listaAeropuertos) {
            Object[] fila = {
                aeropuerto.getIdAeropuerto(),
                aeropuerto.getCodigoIata(),
                aeropuerto.getNombre(),
                aeropuerto.getCiudad(),
                aeropuerto.getPais()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
}
