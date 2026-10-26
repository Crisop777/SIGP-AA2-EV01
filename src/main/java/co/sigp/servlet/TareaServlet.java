
package co.sigp.servlet;

import co.sigp.dao.TareaDAO;
import co.sigp.model.Tarea;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tareas")
public class TareaServlet extends HttpServlet {

    private final TareaDAO tareaDAO = new TareaDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Tarea> tareas = tareaDAO.listar();
            request.setAttribute("tareas", tareas);
        } catch (SQLException e) {
            throw new ServletException("Error al consultar las tareas en MySQL.", e);
        }

        request.setAttribute("metodoUsado", "GET");

        HttpSession session = request.getSession();
        request.setAttribute("mensaje", session.getAttribute("mensaje"));
        session.removeAttribute("mensaje");

        request.getRequestDispatcher("/WEB-INF/tareas.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion == null || accion.isBlank()) {
            accion = "crear";
        }

        HttpSession session = request.getSession();

        try {
            switch (accion) {

                case "crear": {
                    String titulo = request.getParameter("nombreTarea");
                    if (titulo == null || titulo.isBlank()) {
                        titulo = request.getParameter("titulo");
                    }

                    String descripcion = request.getParameter("descripcion");
                    String estado = request.getParameter("estado");
                    String prioridad = request.getParameter("prioridad");

                    if (titulo == null || titulo.isBlank()) {
                        session.setAttribute("mensaje",
                                "Escribe el nombre de la tarea.");
                        break;
                    }

                    if (estado == null || estado.isBlank()) {
                        estado = "Pendiente";
                    }

                    if (descripcion == null) {
                        descripcion = "";
                    }

                    if (prioridad != null && !prioridad.isBlank()) {
                        descripcion = descripcion.isBlank()
                                ? "Prioridad: " + prioridad
                                : descripcion + " | Prioridad: " + prioridad;
                    }

                    Tarea nueva = new Tarea(
                            titulo.trim(),
                            descripcion.trim(),
                            estado.trim()
                    );

                    tareaDAO.crear(nueva);
                    session.setAttribute("mensaje",
                            "Tarea registrada correctamente.");
                    break;
                }

                case "actualizar": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String titulo = request.getParameter("titulo");
                    String descripcion = request.getParameter("descripcion");
                    String estado = request.getParameter("estado");

                    if (titulo == null || titulo.isBlank()
                            || estado == null || estado.isBlank()) {
                        session.setAttribute("mensaje",
                                "El título y el estado son obligatorios.");
                        break;
                    }

                    Tarea tarea = new Tarea(
                            id,
                            titulo.trim(),
                            descripcion == null ? "" : descripcion.trim(),
                            estado.trim()
                    );

                    boolean actualizada = tareaDAO.actualizar(tarea);
                    session.setAttribute("mensaje", actualizada
                            ? "Tarea actualizada correctamente."
                            : "No se encontró la tarea para actualizar.");
                    break;
                }

                case "eliminar": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    boolean eliminada = tareaDAO.eliminar(id);

                    session.setAttribute("mensaje", eliminada
                            ? "Tarea eliminada correctamente."
                            : "No se encontró la tarea para eliminar.");
                    break;
                }

                default:
                    session.setAttribute("mensaje", "Acción no válida.");
                    break;
            }

        } catch (NumberFormatException e) {
            session.setAttribute("mensaje",
                    "El identificador de la tarea no es válido.");
        } catch (SQLException e) {
            throw new ServletException(
                    "No se pudo completar la operación en MySQL.", e);
        }

        response.sendRedirect(request.getContextPath() + "/tareas");
    }
}
