/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOPasajero;
import Model.Pasajero;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Carlos
 */
public class CTRLPasajero {
        DAOPasajero dao;
    
    public CTRLPasajero() {
        dao = new DAOPasajero();
    }
    
    public void agregar(JTextField nombre, JTextField apellido, JTextField email, 
                       JTextField telefono, JTextField documento) {
        dao.agregarPasajero(new Pasajero(
            nombre.getText(),
            apellido.getText(),
            email.getText(),
            telefono.getText(),
            documento.getText()
        ));
    }
    
    public void mostrarPasajeros(JTable tabla) {
        List<Pasajero> listaPasajeros = dao.mostrarPasajeros();
        String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Teléfono", "Documento"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Pasajero pasajero : listaPasajeros) {
            Object[] fila = {
                pasajero.getIdPasajero(),
                pasajero.getNombre(),
                pasajero.getApellido(),
                pasajero.getEmail(),
                pasajero.getTelefono(),
                pasajero.getDocumento()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
    
    public Pasajero buscarPorDocumento(JTextField documento) {
        return dao.buscarPasajeroPorDocumento(documento.getText());
    }
    
    public boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    public void actualizar(int idPasajero, JTextField nombre, JTextField apellido, JTextField email, 
                      JTextField telefono, JTextField documento) {
    Pasajero pasajero = new Pasajero(
        nombre.getText(),
        apellido.getText(),
        email.getText(),
        telefono.getText(),
        documento.getText()
    );
    pasajero.setIdPasajero(idPasajero);
    dao.actualizarPasajero(pasajero);
}

public void eliminar(int idPasajero) {
    dao.eliminarPasajero(idPasajero);
}

}
