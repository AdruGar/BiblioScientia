package com.ecodeup.jdbc;

import java.sql.*;

public class ConMySQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
        String user = "root";
        String password = "1298";

        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a la base de datos exitosa!");

            // Insertar datos
            insertarPersona(connection, "Pedro González", "987654321", "pedronel@mail.com");

            // Actualizar datos
            actualizarPersonaCorreo(connection, 1, "nuevo.correo@mail.com");

            // Eliminar datos
            eliminarPersona(connection, 3);

            // Mostrar los datos de la tabla 'Persona' después de los cambios
            mostrarDatosTablaPersona(connection);

            // Cerrar la conexión
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar una nueva persona
    public static void insertarPersona(Connection connection, String nombre, String telefono, String correo) throws SQLException {
        String query = "INSERT INTO Persona (Nombre, NumeroTelefono, CorreoPrincipal) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, telefono);
        preparedStatement.setString(3, correo);

        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Inserción exitosa: Se ha añadido un nuevo registro.");
        }

        preparedStatement.close();
    }

    // Método para actualizar el correo de una persona
    public static void actualizarPersonaCorreo(Connection connection, int personaID, String nuevoCorreo) throws SQLException {
        String query = "UPDATE Persona SET CorreoPrincipal = ? WHERE PersonaID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nuevoCorreo);
        preparedStatement.setInt(2, personaID);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualización exitosa: El correo ha sido actualizado.");
        }

        preparedStatement.close();
    }

    // Método para eliminar una persona
    public static void eliminarPersona(Connection connection, int personaID) throws SQLException {
        String query = "DELETE FROM Persona WHERE PersonaID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, personaID);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminación exitosa: El registro ha sido eliminado.");
        }

        preparedStatement.close();
    }

    // Método para mostrar los datos de la tabla 'Persona'
    public static void mostrarDatosTablaPersona(Connection connection) throws SQLException {
        String query = "SELECT * FROM Persona";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("\nDatos de la tabla 'Persona':");
        while (resultSet.next()) {
            System.out.println("PersonaID: " + resultSet.getInt("PersonaID"));
            System.out.println("Nombre: " + resultSet.getString("Nombre"));
            System.out.println("NumeroTelefono: " + resultSet.getString("NumeroTelefono"));
            System.out.println("CorreoPrincipal: " + resultSet.getString("CorreoPrincipal"));
            System.out.println("------------------------------");
        }

        resultSet.close();
        statement.close();
    }
}
