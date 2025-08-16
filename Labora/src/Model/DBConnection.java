package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static String bd = "sistema_vuelos";
    static String port = "5432";
    static String login = "postgres";
    static String password = "admin"; 
    static String url = "jdbc:postgresql://localhost:" + port + "/" + bd;
    Connection connection = null;
    
    // conexion con la base de datos
    public DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Conexión exitosa a la base de datos PostgreSQL");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC PostgreSQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    // metodo para desconectar
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}