package com.ecodeup.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMySQL {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
        String user = "root";
        String password = "1298";

        // Intentar cargar el controlador de MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar el driver de MySQL");
        }

        // Establecer la conexión
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("¡Conexión a la base de datos exitosa!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos");
            e.printStackTrace();
        }
    }
}
