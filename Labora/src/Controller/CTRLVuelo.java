/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOVuelo;
import Model.Vuelo;
import java.math.BigDecimal;
import java.sql.Timestamp;             
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox; 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Carlos
 */
public class CTRLVuelo {
       DAOVuelo dao;
    
    public CTRLVuelo() {
        dao = new DAOVuelo();
    }
    
    public void agregar(JTextField numeroVuelo, JComboBox<String> aeropuertoOrigen, 
                       JComboBox<String> aeropuertoDestino, JComboBox<String> avion,
                       JTextField fechaSalida, JTextField fechaLlegada, 
                       JTextField precio, JTextField asientosDisponibles) {
        try {
            String origenSeleccionado = (String) aeropuertoOrigen.getSelectedItem();
            String destinoSeleccionado = (String) aeropuertoDestino.getSelectedItem();
            String avionSeleccionado = (String) avion.getSelectedItem();
            
            int idOrigen = Integer.parseInt(origenSeleccionado.split(" - ")[0]);
            int idDestino = Integer.parseInt(destinoSeleccionado.split(" - ")[0]);
            int idAvion = Integer.parseInt(avionSeleccionado.split(" - ")[0]);
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Timestamp fechaSalidaTs = new Timestamp(formato.parse(fechaSalida.getText()).getTime());
            Timestamp fechaLlegadaTs = new Timestamp(formato.parse(fechaLlegada.getText()).getTime());
            
            dao.agregarVuelo(new Vuelo(
                numeroVuelo.getText().toUpperCase(),
                idOrigen,
                idDestino,
                idAvion,
                fechaSalidaTs,
                fechaLlegadaTs,
                new BigDecimal(precio.getText()),
                Integer.parseInt(asientosDisponibles.getText())
            ));
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error en formato de fecha. Use: yyyy-MM-dd HH:mm");
        }
    }
    
    public void mostrarVuelos(JTable tabla) {
        List<Vuelo> listaVuelos = dao.mostrarVuelos();
        String[] columnas = {"ID", "Número Vuelo", "Origen", "Destino", "Avión", 
                           "Fecha Salida", "Fecha Llegada", "Precio", "Asientos Disponibles"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Vuelo vuelo : listaVuelos) {
            Object[] fila = {
                vuelo.getIdVuelo(),
                vuelo.getNumeroVuelo(),
                vuelo.getIdAeropuertoOrigen(),
                vuelo.getIdAeropuertoDestino(),
                vuelo.getIdAvion(),
                formato.format(vuelo.getFechaSalida()),
                formato.format(vuelo.getFechaLlegada()),
                "$" + vuelo.getPrecio(),
                vuelo.getAsientosDisponibles()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
    
    public void buscarVuelosPorRuta(JTable tabla, JComboBox<String> origen, JComboBox<String> destino) {
        String origenSeleccionado = (String) origen.getSelectedItem();
        String destinoSeleccionado = (String) destino.getSelectedItem();
        
        int idOrigen = Integer.parseInt(origenSeleccionado.split(" - ")[0]);
        int idDestino = Integer.parseInt(destinoSeleccionado.split(" - ")[0]);
        
        List<Vuelo> vuelosEncontrados = dao.buscarVuelosPorRuta(idOrigen, idDestino);
        String[] columnas = {"ID", "Número Vuelo", "Fecha Salida", "Fecha Llegada", "Precio", "Asientos Disponibles"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Vuelo vuelo : vuelosEncontrados) {
            Object[] fila = {
                vuelo.getIdVuelo(),
                vuelo.getNumeroVuelo(),
                formato.format(vuelo.getFechaSalida()),
                formato.format(vuelo.getFechaLlegada()),
                "$" + vuelo.getPrecio(),
                vuelo.getAsientosDisponibles()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
    public void actualizar(int idVuelo, JTextField numeroVuelo, JComboBox<String> aeropuertoOrigen, 
                      JComboBox<String> aeropuertoDestino, JComboBox<String> avion,
                      JTextField fechaSalida, JTextField fechaLlegada, 
                      JTextField precio, JTextField asientosDisponibles) {
    try {
        String origenSeleccionado = (String) aeropuertoOrigen.getSelectedItem();
        String destinoSeleccionado = (String) aeropuertoDestino.getSelectedItem();
        String avionSeleccionado = (String) avion.getSelectedItem();
        
        int idOrigen = Integer.parseInt(origenSeleccionado.split(" - ")[0]);
        int idDestino = Integer.parseInt(destinoSeleccionado.split(" - ")[0]);
        int idAvion = Integer.parseInt(avionSeleccionado.split(" - ")[0]);
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Timestamp fechaSalidaTs = new Timestamp(formato.parse(fechaSalida.getText()).getTime());
        Timestamp fechaLlegadaTs = new Timestamp(formato.parse(fechaLlegada.getText()).getTime());
        
        Vuelo vuelo = new Vuelo(
            numeroVuelo.getText().toUpperCase(),
            idOrigen,
            idDestino,
            idAvion,
            fechaSalidaTs,
            fechaLlegadaTs,
            new BigDecimal(precio.getText()),
            Integer.parseInt(asientosDisponibles.getText())
        );
        vuelo.setIdVuelo(idVuelo);
        
        dao.actualizarVuelo(vuelo);
        
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar vuelo: " + e.getMessage());
    }
    }
    public void eliminar(int idVuelo) {
    try {
        dao.eliminarVuelo(idVuelo);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar vuelo: " + e.getMessage());
    }
}
}
