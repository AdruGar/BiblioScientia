package com.ecodeup.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecodeup.jdbc.ConMySQL;


@WebServlet("/miServlet")
public class MiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        // Validar los valores recibidos del formulario
        if (nombre == null) nombre = "Desconocido";
        if (telefono == null) telefono = "No proporcionado";
        if (correo == null) correo = "No proporcionado";

        // Conectar a la base de datos y hacer la inserción
        try {
            Connection connection = ConMySQL.getConnection();  // Método estático de tu clase ConMySQL para obtener la conexión

            // Query de inserción
            String query = "INSERT INTO Persona (Nombre, NumeroTelefono, CorreoPrincipal) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, telefono);
            preparedStatement.setString(3, correo);

            // Ejecutar la inserción
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserción exitosa: Se ha añadido un nuevo registro.");
            }

            // Cerrar la conexión
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Enviar los datos al JSP para mostrarlos
        request.setAttribute("nombre", nombre);
        request.setAttribute("telefono", telefono);
        request.setAttribute("correo", correo);

        // Redirigir al JSP de resultados
        request.getRequestDispatcher("/jsp/resultado.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
