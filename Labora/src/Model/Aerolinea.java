/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luis Carlos
 */
public class Aerolinea {
        private int idAerolinea;
    private String codigo;
    private String nombre;
    private String paisOrigen;

    public Aerolinea() {}
    
    public Aerolinea(String codigo, String nombre, String paisOrigen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
    }

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    @Override
    public String toString() {
        return "Aerolinea{" +
                "idAerolinea=" + idAerolinea +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                '}';
    }
}
