/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Luis Carlos
 */
public class Reserva {
       private int idReserva;
    private String codigoReserva;
    private int idPasajero;
    private int idVuelo;
    private Timestamp fechaReserva;
    private String estado;
    private int asientosReservados;
    private BigDecimal precioTotal;

    public Reserva() {}
    
    public Reserva(String codigoReserva, int idPasajero, int idVuelo, 
                   String estado, int asientosReservados, BigDecimal precioTotal) {
        this.codigoReserva = codigoReserva;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.estado = estado;
        this.asientosReservados = asientosReservados;
        this.precioTotal = precioTotal;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(int asientosReservados) {
        this.asientosReservados = asientosReservados;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }
    

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", codigoReserva='" + codigoReserva + '\'' +
                ", idPasajero=" + idPasajero +
                ", idVuelo=" + idVuelo +
                ", fechaReserva=" + fechaReserva +
                ", estado='" + estado + '\'' +
                ", asientosReservados=" + asientosReservados +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
