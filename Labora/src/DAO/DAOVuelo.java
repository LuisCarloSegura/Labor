/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DBConnection;
import Model.Vuelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Timestamp;

/**
 *
 * @author Luis Carlos
 */
public class DAOVuelo {
        
    public void agregarVuelo(Vuelo vuelo) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO vuelos (numero_vuelo, id_aeropuerto_origen, id_aeropuerto_destino, id_avion, fecha_salida, fecha_llegada, precio, asientos_disponibles) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, vuelo.getNumeroVuelo());
            ps.setInt(2, vuelo.getIdAeropuertoOrigen());
            ps.setInt(3, vuelo.getIdAeropuertoDestino());
            ps.setInt(4, vuelo.getIdAvion());
            ps.setTimestamp(5, vuelo.getFechaSalida());
            ps.setTimestamp(6, vuelo.getFechaLlegada());
            ps.setBigDecimal(7, vuelo.getPrecio());
            ps.setInt(8, vuelo.getAsientosDisponibles());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el vuelo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el vuelo, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Vuelo> mostrarVuelos() {
        List<Vuelo> vuelos = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM vuelos";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(resultSet.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(resultSet.getString("numero_vuelo"));
                vuelo.setIdAeropuertoOrigen(resultSet.getInt("id_aeropuerto_origen"));
                vuelo.setIdAeropuertoDestino(resultSet.getInt("id_aeropuerto_destino"));
                vuelo.setIdAvion(resultSet.getInt("id_avion"));
                vuelo.setFechaSalida(resultSet.getTimestamp("fecha_salida"));
                vuelo.setFechaLlegada(resultSet.getTimestamp("fecha_llegada"));
                vuelo.setPrecio(resultSet.getBigDecimal("precio"));
                vuelo.setAsientosDisponibles(resultSet.getInt("asientos_disponibles"));
                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los vuelos: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return vuelos;
    }
    
    public List<Vuelo> buscarVuelosPorRuta(int idOrigen, int idDestino) {
        List<Vuelo> vuelos = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM vuelos WHERE id_aeropuerto_origen=? AND id_aeropuerto_destino=? AND asientos_disponibles > 0";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ps.setInt(1, idOrigen);
            ps.setInt(2, idDestino);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(resultSet.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(resultSet.getString("numero_vuelo"));
                vuelo.setIdAeropuertoOrigen(resultSet.getInt("id_aeropuerto_origen"));
                vuelo.setIdAeropuertoDestino(resultSet.getInt("id_aeropuerto_destino"));
                vuelo.setIdAvion(resultSet.getInt("id_avion"));
                vuelo.setFechaSalida(resultSet.getTimestamp("fecha_salida"));
                vuelo.setFechaLlegada(resultSet.getTimestamp("fecha_llegada"));
                vuelo.setPrecio(resultSet.getBigDecimal("precio"));
                vuelo.setAsientosDisponibles(resultSet.getInt("asientos_disponibles"));
                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar vuelos: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return vuelos;
    }
    
    public void actualizarAsientosDisponibles(int idVuelo, int nuevosAsientos) {
        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE vuelos SET asientos_disponibles=? WHERE id_vuelo=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, nuevosAsientos);
            ps.setInt(2, idVuelo);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar asientos: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    public void actualizarVuelo(Vuelo vuelo) {
    DBConnection db = new DBConnection();
    String consultaSQL = "UPDATE vuelos SET numero_vuelo=?, id_aeropuerto_origen=?, id_aeropuerto_destino=?, id_avion=?, fecha_salida=?, fecha_llegada=?, precio=?, asientos_disponibles=? WHERE id_vuelo=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setString(1, vuelo.getNumeroVuelo());
        ps.setInt(2, vuelo.getIdAeropuertoOrigen());
        ps.setInt(3, vuelo.getIdAeropuertoDestino());
        ps.setInt(4, vuelo.getIdAvion());
        ps.setTimestamp(5, vuelo.getFechaSalida());
        ps.setTimestamp(6, vuelo.getFechaLlegada());
        ps.setBigDecimal(7, vuelo.getPrecio());
        ps.setInt(8, vuelo.getAsientosDisponibles());
        ps.setInt(9, vuelo.getIdVuelo());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Vuelo actualizado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar vuelo: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}

public void eliminarVuelo(int idVuelo) {
    DBConnection db = new DBConnection();
    String consultaSQL = "DELETE FROM vuelos WHERE id_vuelo=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setInt(1, idVuelo);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Vuelo eliminado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar vuelo: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}
}
