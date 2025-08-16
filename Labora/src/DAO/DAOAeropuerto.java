/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Aeropuerto;
import Model.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Carlos
 */
public class DAOAeropuerto {
            
    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO aeropuertos (codigo_iata, nombre, ciudad, pais) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, aeropuerto.getCodigoIata());
            ps.setString(2, aeropuerto.getNombre());
            ps.setString(3, aeropuerto.getCiudad());
            ps.setString(4, aeropuerto.getPais());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el aeropuerto");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el aeropuerto, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Aeropuerto> mostrarAeropuertos() {
        List<Aeropuerto> aeropuertos = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM aeropuertos";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Aeropuerto aeropuerto = new Aeropuerto();
                aeropuerto.setIdAeropuerto(resultSet.getInt("id_aeropuerto"));
                aeropuerto.setCodigoIata(resultSet.getString("codigo_iata"));
                aeropuerto.setNombre(resultSet.getString("nombre"));
                aeropuerto.setCiudad(resultSet.getString("ciudad"));
                aeropuerto.setPais(resultSet.getString("pais"));
                aeropuertos.add(aeropuerto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los aeropuertos: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aeropuertos;
    }
    
    public List<String> llenarComboAeropuertos() {
        List<String> aeropuertos = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT codigo_iata, nombre FROM aeropuertos";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                aeropuertos.add(resultSet.getString("codigo_iata") + " - " + resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar aeropuertos: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aeropuertos;
    }
    
    public void actualizarAeropuerto(Aeropuerto aeropuerto) {
        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE aeropuertos SET codigo_iata=?, nombre=?, ciudad=?, pais=? WHERE id_aeropuerto=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, aeropuerto.getCodigoIata());
            ps.setString(2, aeropuerto.getNombre());
            ps.setString(3, aeropuerto.getCiudad());
            ps.setString(4, aeropuerto.getPais());
            ps.setInt(5, aeropuerto.getIdAeropuerto());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aeropuerto actualizado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar aeropuerto: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public void eliminarAeropuerto(int idAeropuerto) {
        DBConnection db = new DBConnection();
        String consultaSQL = "DELETE FROM aeropuertos WHERE id_aeropuerto=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, idAeropuerto);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aeropuerto eliminado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar aeropuerto: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
}
