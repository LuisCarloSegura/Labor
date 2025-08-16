/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOReserva;
import DAO.DAOVuelo;
import Model.Reserva;
import Model.Vuelo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Carlos
 */
public class CTRLReserva {
    DAOReserva dao;
    DAOVuelo daoVuelo;
    
    public CTRLReserva() {
        dao = new DAOReserva();
        daoVuelo = new DAOVuelo();
    }
    
    public void agregar(JTextField idPasajero, JTextField idVuelo, JTextField asientosReservados, JTextField precioTotal) {
        try {
            String codigoReserva = generarCodigoReserva();
            int vueloId = Integer.parseInt(idVuelo.getText());
            int asientosReservadosInt = Integer.parseInt(asientosReservados.getText());
            if (verificarDisponibilidadAsientos(vueloId, asientosReservadosInt)) {
                dao.agregarReserva(new Reserva(
                    codigoReserva,
                    Integer.parseInt(idPasajero.getText()),
                    vueloId,
                    "CONFIRMADA",
                    asientosReservadosInt,
                    new BigDecimal(precioTotal.getText())
                ));
                actualizarAsientosVuelo(vueloId, asientosReservadosInt);
                
                JOptionPane.showMessageDialog(null, 
                    "Reserva creada exitosamente.\nCódigo de reserva: " + codigoReserva);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No hay suficientes asientos disponibles para este vuelo.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en formato numérico: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la reserva: " + e.getMessage());
        }
    }
    
    public void mostrarReservas(JTable tabla) {
        List<Reserva> listaReservas = dao.mostrarReservas();
        String[] columnas = {"ID", "Código Reserva", "ID Pasajero", "ID Vuelo", 
                           "Fecha Reserva", "Estado", "Asientos", "Precio Total"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Reserva reserva : listaReservas) {
            Object[] fila = {
                reserva.getIdReserva(),
                reserva.getCodigoReserva(),
                reserva.getIdPasajero(),
                reserva.getIdVuelo(),
                reserva.getFechaReserva() != null ? formato.format(reserva.getFechaReserva()) : "N/A",
                reserva.getEstado(),
                reserva.getAsientosReservados(),
                "$" + reserva.getPrecioTotal()
            };
            modelo.addRow(fila);
        }
        tabla.setModel(modelo);
    }
    
    public Reserva buscarPorCodigo(JTextField codigoReserva) {
        try {
            if (codigoReserva.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un código de reserva");
                return null;
            }
            
            Reserva reserva = dao.buscarReservaPorCodigo(codigoReserva.getText().trim().toUpperCase());
            
            if (reserva == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con ese código");
            }
            
            return reserva;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar la reserva: " + e.getMessage());
            return null;
        }
    }
    
    public void cancelarReserva(JTextField codigoReserva) {
        try {
            if (codigoReserva.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un código de reserva");
                return;
            }
            Reserva reserva = dao.buscarReservaPorCodigo(codigoReserva.getText().trim().toUpperCase());
            
            if (reserva != null && !"CANCELADA".equals(reserva.getEstado())) {
                dao.cancelarReserva(codigoReserva.getText().trim().toUpperCase());
                devolverAsientosVuelo(reserva.getIdVuelo(), reserva.getAsientosReservados());
                
                JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente");
            } else if (reserva != null && "CANCELADA".equals(reserva.getEstado())) {
                JOptionPane.showMessageDialog(null, "Esta reserva ya está cancelada");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con ese código");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cancelar la reserva: " + e.getMessage());
        }
    }
    
    public BigDecimal calcularPrecioTotal(BigDecimal precioUnitario, int cantidadAsientos) {
        if (precioUnitario == null || cantidadAsientos <= 0) {
            return BigDecimal.ZERO;
        }
        return precioUnitario.multiply(new BigDecimal(cantidadAsientos));
    }
    
    public boolean verificarDisponibilidadAsientos(int idVuelo, int asientosSolicitados) {
        try {
            List<Vuelo> vuelos = daoVuelo.mostrarVuelos();
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getIdVuelo() == idVuelo) {
                    return vuelo.getAsientosDisponibles() >= asientosSolicitados;
                }
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar disponibilidad: " + e.getMessage());
            return false;
        }
    }
    
    private void actualizarAsientosVuelo(int idVuelo, int asientosReservados) {
        try {
            List<Vuelo> vuelos = daoVuelo.mostrarVuelos();
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getIdVuelo() == idVuelo) {
                    int nuevosAsientos = vuelo.getAsientosDisponibles() - asientosReservados;
                    daoVuelo.actualizarAsientosDisponibles(idVuelo, nuevosAsientos);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar asientos: " + e.getMessage());
        }
    }
    
    private void devolverAsientosVuelo(int idVuelo, int asientosADevolver) {
        try {
            List<Vuelo> vuelos = daoVuelo.mostrarVuelos();
            for (Vuelo vuelo : vuelos) {
                if (vuelo.getIdVuelo() == idVuelo) {
                    int nuevosAsientos = vuelo.getAsientosDisponibles() + asientosADevolver;
                    daoVuelo.actualizarAsientosDisponibles(idVuelo, nuevosAsientos);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al devolver asientos: " + e.getMessage());
        }
    }
    
    public void mostrarReservasPorPasajero(JTable tabla, int idPasajero) {
        List<Reserva> todasLasReservas = dao.mostrarReservas();
        String[] columnas = {"ID", "Código Reserva", "ID Vuelo", 
                           "Fecha Reserva", "Estado", "Asientos", "Precio Total"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Reserva reserva : todasLasReservas) {
            if (reserva.getIdPasajero() == idPasajero) {
                Object[] fila = {
                    reserva.getIdReserva(),
                    reserva.getCodigoReserva(),
                    reserva.getIdVuelo(),
                    reserva.getFechaReserva() != null ? formato.format(reserva.getFechaReserva()) : "N/A",
                    reserva.getEstado(),
                    reserva.getAsientosReservados(),
                    "$" + reserva.getPrecioTotal()
                };
                modelo.addRow(fila);
            }
        }
        tabla.setModel(modelo);
    }
    
    public boolean validarDatosReserva(String idPasajero, String idVuelo, String asientosReservados, String precioTotal) {
        try {
            if (idPasajero.trim().isEmpty() || idVuelo.trim().isEmpty() || 
                asientosReservados.trim().isEmpty() || precioTotal.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                return false;
            }
            
            int pasajero = Integer.parseInt(idPasajero);
            int vuelo = Integer.parseInt(idVuelo);
            int asientos = Integer.parseInt(asientosReservados);
            BigDecimal precio = new BigDecimal(precioTotal);
            
            if (pasajero <= 0 || vuelo <= 0 || asientos <= 0 || precio.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(null, "Todos los valores deben ser positivos");
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato numérico inválido");
            return false;
        }
    }
    
    private String generarCodigoReserva() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            codigo.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        return codigo.toString();
    }
}