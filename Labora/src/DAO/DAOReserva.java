/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.DBConnection;
import Model.Reserva;
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
public class DAOReserva {

    public void agregarReserva(Reserva reserva) {
        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO reservas (codigo_reserva, id_pasajero, id_vuelo, estado, asientos_reservados, precio_total) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, reserva.getCodigoReserva());
            ps.setInt(2, reserva.getIdPasajero());
            ps.setInt(3, reserva.getIdVuelo());
            ps.setString(4, reserva.getEstado());
            ps.setInt(5, reserva.getAsientosReservados());
            ps.setBigDecimal(6, reserva.getPrecioTotal());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la reserva");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente la reserva, error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
    
    public List<Reserva> mostrarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM reservas";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(resultSet.getInt("id_reserva"));
                reserva.setCodigoReserva(resultSet.getString("codigo_reserva"));
                reserva.setIdPasajero(resultSet.getInt("id_pasajero"));
                reserva.setIdVuelo(resultSet.getInt("id_vuelo"));
                reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva"));
                reserva.setEstado(resultSet.getString("estado"));
                reserva.setAsientosReservados(resultSet.getInt("asientos_reservados"));
                reserva.setPrecioTotal(resultSet.getBigDecimal("precio_total"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar las reservas: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return reservas;
    }
    
    public Reserva buscarReservaPorCodigo(String codigoReserva) {
        Reserva reserva = null;
        DBConnection db = new DBConnection();
        String SQLConsulta = "SELECT * FROM reservas WHERE codigo_reserva=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQLConsulta);
            ps.setString(1, codigoReserva);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                reserva = new Reserva();
                reserva.setIdReserva(resultSet.getInt("id_reserva"));
                reserva.setCodigoReserva(resultSet.getString("codigo_reserva"));
                reserva.setIdPasajero(resultSet.getInt("id_pasajero"));
                reserva.setIdVuelo(resultSet.getInt("id_vuelo"));
                reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva"));
                reserva.setEstado(resultSet.getString("estado"));
                reserva.setAsientosReservados(resultSet.getInt("asientos_reservados"));
                reserva.setPrecioTotal(resultSet.getBigDecimal("precio_total"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar reserva: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return reserva;
    }
    
    public void cancelarReserva(String codigoReserva) {
        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE reservas SET estado='CANCELADA' WHERE codigo_reserva=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, codigoReserva);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Reserva cancelada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cancelar reserva: " + e.getMessage());
        } finally {
            db.disconnect();
        }
    }
}
