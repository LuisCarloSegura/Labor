/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Avion;
import Model.DBConnection;
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
public class DAOAvion {
        
    public void agregarAvion(Avion avion) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO aviones (modelo, capacidad, id_aerolinea) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, avion.getModelo());
            ps.setInt(2, avion.getCapacidad());
            ps.setInt(3, avion.getIdAerolinea());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el avión");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el avión, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Avion> mostrarAviones() {
        List<Avion> aviones = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM aviones";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Avion avion = new Avion();
                avion.setIdAvion(resultSet.getInt("id_avion"));
                avion.setModelo(resultSet.getString("modelo"));
                avion.setCapacidad(resultSet.getInt("capacidad"));
                avion.setIdAerolinea(resultSet.getInt("id_aerolinea"));
                aviones.add(avion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los aviones: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aviones;
    }
    
    public List<String> llenarComboAviones() {
        List<String> aviones = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT id_avion, modelo FROM aviones";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                aviones.add(resultSet.getInt("id_avion") + " - " + resultSet.getString("modelo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar aviones: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aviones;
    }
    
    public void actualizarAvion(Avion avion) {
    DBConnection db = new DBConnection();
    String consultaSQL = "UPDATE aviones SET modelo=?, capacidad=?, id_aerolinea=? WHERE id_avion=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setString(1, avion.getModelo());
        ps.setInt(2, avion.getCapacidad());
        ps.setInt(3, avion.getIdAerolinea());
        ps.setInt(4, avion.getIdAvion());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Avión actualizado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar avión: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}

public void eliminarAvion(int idAvion) {
    DBConnection db = new DBConnection();
    String consultaSQL = "DELETE FROM aviones WHERE id_avion=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setInt(1, idAvion);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Avión eliminado correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar avión: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}
}
