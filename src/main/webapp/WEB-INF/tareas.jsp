
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="co.sigp.model.Tarea" %>
<%!
    private String escapar(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SIGP | Gestión de tareas</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f3f6fb;
               margin: 0; color: #172033; }
        main { max-width: 900px; margin: 35px auto; padding: 28px;
               background: white; border-radius: 14px;
               box-shadow: 0 5px 22px #0001; }
        h1, h2 { color: #173b70; }
        .item { padding: 16px; background: #eef3fa;
                border-radius: 8px; margin: 12px 0; }
        input, textarea, select, button {
            box-sizing: border-box; padding: 10px; margin: 5px 0;
            max-width: 100%; border-radius: 6px; border: 1px solid #ccd5e2;
        }
        input, textarea, select { width: 100%; }
        button { cursor: pointer; background: #174a85;
                 color: white; border: 0; }
        .eliminar { background: #a52a2a; }
        .acciones { display: flex; gap: 8px; flex-wrap: wrap; }
        .acciones form { margin: 0; }
        .mensaje { padding: 12px; background: #e5f6e9; border-radius: 7px; }
        a { color: #174a85; }
        label { display: block; margin-top: 8px; font-weight: bold; }
    </style>
</head>
<body>
<main>
    <h1>Gestión de tareas SIGP</h1>
    <p>Listado consultado desde MySQL mediante el método
       <strong><%= escapar((String) request.getAttribute("metodoUsado")) %></strong>.</p>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null && !mensaje.isBlank()) {
    %>
        <p class="mensaje"><strong><%= escapar(mensaje) %></strong></p>
    <%
        }

        List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");
    %>

    <h2>Registrar una tarea</h2>
    <form action="<%= request.getContextPath() %>/tareas" method="post">
        <input type="hidden" name="accion" value="crear">

        <label for="nombreTarea">Título</label>
        <input id="nombreTarea" name="nombreTarea" maxlength="100"
               required placeholder="Ej. Diseñar módulo de reportes">

        <label for="descripcion">Descripción</label>
        <textarea id="descripcion" name="descripcion" maxlength="500"
                  rows="3" placeholder="Detalles de la tarea"></textarea>

        <label for="estado">Estado</label>
        <select id="estado" name="estado">
            <option value="Pendiente">Pendiente</option>
            <option value="En progreso">En progreso</option>
            <option value="Completada">Completada</option>
        </select>

        <button type="submit">Registrar tarea</button>
    </form>

    <hr>
    <h2>Tareas registradas</h2>

    <%
        if (tareas == null || tareas.isEmpty()) {
    %>
        <p>Aún no hay tareas registradas en la base de datos.</p>
    <%
        } else {
            for (Tarea tarea : tareas) {
    %>
        <section class="item">
            <h3>#<%= tarea.getId() %> — <%= escapar(tarea.getTitulo()) %></h3>
            <p><strong>Descripción:</strong>
               <%= escapar(tarea.getDescripcion()) %></p>
            <p><strong>Estado:</strong> <%= escapar(tarea.getEstado()) %></p>

            <details>
                <summary>Editar tarea</summary>
                <form action="<%= request.getContextPath() %>/tareas" method="post">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= tarea.getId() %>">

                    <label>Título</label>
                    <input name="titulo" maxlength="100" required
                           value="<%= escapar(tarea.getTitulo()) %>">

                    <label>Descripción</label>
                    <textarea name="descripcion" maxlength="500"
                              rows="3"><%= escapar(tarea.getDescripcion()) %></textarea>

                    <label>Estado</label>
                    <select name="estado">
                        <option value="Pendiente"
                            <%= "Pendiente".equals(tarea.getEstado()) ? "selected" : "" %>>
                            Pendiente</option>
                        <option value="En progreso"
                            <%= "En progreso".equals(tarea.getEstado()) ? "selected" : "" %>>
                            En progreso</option>
                        <option value="Completada"
                            <%= "Completada".equals(tarea.getEstado()) ? "selected" : "" %>>
                            Completada</option>
                    </select>

                    <button type="submit">Guardar cambios</button>
                </form>
            </details>

            <div class="acciones">
                <form action="<%= request.getContextPath() %>/tareas" method="post"
                      onsubmit="return confirm('¿Seguro que deseas eliminar esta tarea?');">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="id" value="<%= tarea.getId() %>">
                    <button class="eliminar" type="submit">Eliminar</button>
                </form>
            </div>
        </section>
    <%
            }
        }
    %>

    <p><a href="<%= request.getContextPath() %>/tareas">Actualizar listado (GET)</a></p>
</main>
</body>
</html>
