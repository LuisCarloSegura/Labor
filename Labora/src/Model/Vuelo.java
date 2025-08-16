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
public class Vuelo {
        private int idVuelo;
    private String numeroVuelo;
    private int idAeropuertoOrigen;
    private int idAeropuertoDestino;
    private int idAvion;
    private Timestamp fechaSalida;
    private Timestamp fechaLlegada;
    private BigDecimal precio;
    private int asientosDisponibles;
    
    // Constructores
    public Vuelo() {}
    
    public Vuelo(String numeroVuelo, int idAeropuertoOrigen, int idAeropuertoDestino, 
                 int idAvion, Timestamp fechaSalida, Timestamp fechaLlegada, 
                 BigDecimal precio, int asientosDisponibles) {
        this.numeroVuelo = numeroVuelo;
        this.idAeropuertoOrigen = idAeropuertoOrigen;
        this.idAeropuertoDestino = idAeropuertoDestino;
        this.idAvion = idAvion;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public int getIdAeropuertoOrigen() {
        return idAeropuertoOrigen;
    }

    public void setIdAeropuertoOrigen(int idAeropuertoOrigen) {
        this.idAeropuertoOrigen = idAeropuertoOrigen;
    }

    public int getIdAeropuertoDestino() {
        return idAeropuertoDestino;
    }

    public void setIdAeropuertoDestino(int idAeropuertoDestino) {
        this.idAeropuertoDestino = idAeropuertoDestino;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Timestamp getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Timestamp fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
    

    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo=" + idVuelo +
                ", numeroVuelo='" + numeroVuelo + '\'' +
                ", idAeropuertoOrigen=" + idAeropuertoOrigen +
                ", idAeropuertoDestino=" + idAeropuertoDestino +
                ", idAvion=" + idAvion +
                ", fechaSalida=" + fechaSalida +
                ", fechaLlegada=" + fechaLlegada +
                ", precio=" + precio +
                ", asientosDisponibles=" + asientosDisponibles +
                '}';
    }
}
