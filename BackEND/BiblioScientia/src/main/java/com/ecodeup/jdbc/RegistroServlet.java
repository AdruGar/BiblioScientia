package com.ecodeup.jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configuración CORS
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Cambia al dominio de tu frontend
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        try {
            Connection conn = ConMySQL.getConnection();

            String sql = "INSERT INTO CuentaUsuario (Usuario, Contraseña, Rol) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password); // Podrías cifrarla después
            stmt.setString(3, rol);

            int filas = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (filas > 0) {
                // Registro exitoso → redirigir a login.jsp o página de bienvenida
                request.setAttribute("mensaje", "Registro exitoso, puedes iniciar sesión.");
                response.setContentType("application/json");
                response.getWriter().write("{\"mensaje\": \"Registro exitoso\"}");
                // request.getRequestDispatcher("/pages/registro_exitoso.jsp").forward(request, response);
            } else {
                response.sendError(500, "Error: no se insertó el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Duplicate entry")) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Este correo ya está registrado.\"}");
            } else {
                response.sendError(500, "Error al registrar: " + errorMessage);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/registro_exitoso.jsp");
    }
}
