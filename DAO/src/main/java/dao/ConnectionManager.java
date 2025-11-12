package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_demo?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";         // 🔹 cambia por tu usuario
    private static final String PASSWORD = "3112004"; // 🔹 cambia por tu contraseña

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: Driver JDBC de MySQL no encontrado.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
