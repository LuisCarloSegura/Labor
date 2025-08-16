/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luis Carlos
 */
public class Avion {
    private int idAvion;
    private String modelo;
    private int capacidad;
    private int idAerolinea;

    public Avion() {}
    
    public Avion(String modelo, int capacidad, int idAerolinea) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.idAerolinea = idAerolinea;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }
    

    @Override
    public String toString() {
        return "Avion{" +
                "idAvion=" + idAvion +
                ", modelo='" + modelo + '\'' +
                ", capacidad=" + capacidad +
                ", idAerolinea=" + idAerolinea +
                '}';
    }
}
