/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Aerolinea;
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
public class DAOAerolinea {
       
    public void agregarAerolinea(Aerolinea aerolinea) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO aerolineas (codigo, nombre, pais_origen) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, aerolinea.getCodigo());
            ps.setString(2, aerolinea.getNombre());
            ps.setString(3, aerolinea.getPaisOrigen());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la aerolínea");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente la aerolínea, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Aerolinea> mostrarAerolineas() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM aerolineas";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Aerolinea aerolinea = new Aerolinea();
                aerolinea.setIdAerolinea(resultSet.getInt("id_aerolinea"));
                aerolinea.setCodigo(resultSet.getString("codigo"));
                aerolinea.setNombre(resultSet.getString("nombre"));
                aerolinea.setPaisOrigen(resultSet.getString("pais_origen"));
                aerolineas.add(aerolinea);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar las aerolíneas: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aerolineas;
    }
    
    public List<String> llenarComboAerolineas() {
        List<String> aerolineas = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT codigo, nombre FROM aerolineas";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                aerolineas.add(resultSet.getString("codigo") + " - " + resultSet.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar aerolíneas: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return aerolineas;
    }
    
    public void actualizarAerolinea(Aerolinea aerolinea) {
    DBConnection db = new DBConnection();
    String consultaSQL = "UPDATE aerolineas SET codigo=?, nombre=?, pais_origen=? WHERE id_aerolinea=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setString(1, aerolinea.getCodigo());
        ps.setString(2, aerolinea.getNombre());
        ps.setString(3, aerolinea.getPaisOrigen());
        ps.setInt(4, aerolinea.getIdAerolinea());
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Aerolínea actualizada correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al actualizar aerolínea: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}
    
    public void eliminarAerolinea(int idAerolinea) {
    DBConnection db = new DBConnection();
    String consultaSQL = "DELETE FROM aerolineas WHERE id_aerolinea=?";
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setInt(1, idAerolinea);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Aerolínea eliminada correctamente");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar aerolínea: " + e.getMessage());
    } finally {
        db.disconnect();
    }
}
    
    
}
