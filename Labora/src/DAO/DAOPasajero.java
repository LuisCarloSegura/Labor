/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DBConnection;
import Model.Pasajero;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luis Carlos
 */
public class DAOPasajero {
        
    public void agregarPasajero(Pasajero pasajero) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO pasajeros (nombre, apellido, email, telefono, documento) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, pasajero.getNombre());
            ps.setString(2, pasajero.getApellido());
            ps.setString(3, pasajero.getEmail());
            ps.setString(4, pasajero.getTelefono());
            ps.setString(5, pasajero.getDocumento());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el pasajero");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el pasajero, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Pasajero> mostrarPasajeros() {
        List<Pasajero> pasajeros = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM pasajeros";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                pasajero.setNombre(resultSet.getString("nombre"));
                pasajero.setApellido(resultSet.getString("apellido"));
                pasajero.setEmail(resultSet.getString("email"));
                pasajero.setTelefono(resultSet.getString("telefono"));
                pasajero.setDocumento(resultSet.getString("documento"));
                pasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los pasajeros: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return pasajeros;
    }
    
    public Pasajero buscarPasajeroPorDocumento(String documento) {
        Pasajero pasajero = null;
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM pasajeros WHERE documento=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ps.setString(1, documento);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                pasajero = new Pasajero();
                pasajero.setIdPasajero(resultSet.getInt("id_pasajero"));
                pasajero.setNombre(resultSet.getString("nombre"));
                pasajero.setApellido(resultSet.getString("apellido"));
                pasajero.setEmail(resultSet.getString("email"));
                pasajero.setTelefono(resultSet.getString("telefono"));
                pasajero.setDocumento(resultSet.getString("documento"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar pasajero: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return pasajero;
    }
    public void actualizarPasajero(Pasajero pasajero) {
    DBConnection db = new DBConnection();
    String consultaSQL = "UPDATE pasajeros SET nombre=?, apellido=?, email=?, telefono=?, documento=? WHERE id_pasajero=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setString(1, pasajero.getNombre());
        ps.setString(2, pasajero.getApellido());
        ps.setString(3, pasajero.getEmail());
        ps.setString(4, pasajero.getTelefono());
        ps.setString(5, pasajero.getDocumento());
        ps.setInt(6, pasajero.getIdPasajero());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Pasajero actualizado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar pasajero: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}

public void eliminarPasajero(int idPasajero) {
    DBConnection db = new DBConnection();
    String consultaSQL = "DELETE FROM pasajeros WHERE id_pasajero=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setInt(1, idPasajero);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Pasajero eliminado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar pasajero: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}
}
