
package co.sigp.dao;

import co.sigp.config.DatabaseConnection;
import co.sigp.model.Tarea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    // READ: consultar todas las tareas
    public List<Tarea> listar() throws SQLException {
        List<Tarea> tareas = new ArrayList<>();

        String sql = "SELECT id, titulo, descripcion, estado FROM tareas";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Tarea tarea = new Tarea(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getString("descripcion"),
                    resultado.getString("estado")
                );

                tareas.add(tarea);
            }
        }

        return tareas;
    }

    // CREATE: registrar una tarea
    public boolean crear(Tarea tarea) throws SQLException {
        String sql = """
            INSERT INTO tareas (titulo, descripcion, estado)
            VALUES (?, ?, ?)
            """;

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, tarea.getTitulo());
            sentencia.setString(2, tarea.getDescripcion());
            sentencia.setString(3, tarea.getEstado());

            return sentencia.executeUpdate() > 0;
        }
    }

    // UPDATE: actualizar una tarea existente
    public boolean actualizar(Tarea tarea) throws SQLException {
        String sql = """
            UPDATE tareas
            SET titulo = ?, descripcion = ?, estado = ?
            WHERE id = ?
            """;

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, tarea.getTitulo());
            sentencia.setString(2, tarea.getDescripcion());
            sentencia.setString(3, tarea.getEstado());
            sentencia.setInt(4, tarea.getId());

            return sentencia.executeUpdate() > 0;
        }
    }

    // DELETE: eliminar una tarea por su identificador
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tareas WHERE id = ?";

        try (Connection conexion = DatabaseConnection.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;
        }
    }
}
