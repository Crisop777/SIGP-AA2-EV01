# Guía de pruebas funcionales — SIGP EV01

Ejecutar después de crear la base con `docs/base_datos.sql`, configurar las variables `SIGP_DB_USER` y `SIGP_DB_PASSWORD`, compilar y desplegar el WAR en Tomcat 10.1.

| ID | Acción | Resultado esperado |
|---|---|---|
| CP-01 | Abrir `http://localhost:8080/SIGP/` | Se muestra el módulo de gestión de tareas. |
| CP-02 | Crear una tarea con título, descripción y estado | Aparece un mensaje de éxito y la tarea en la lista. |
| CP-03 | Consultar o recargar el listado | La tarea permanece porque está guardada en MySQL. |
| CP-04 | Editar una tarea y guardar | Los cambios aparecen en el listado. |
| CP-05 | Eliminar una tarea | La tarea deja de aparecer en el listado. |
| CP-06 | Intentar crear una tarea sin título | La aplicación informa que el título es obligatorio. |

## Evidencia sugerida

Guardar capturas reales de la aplicación desplegada que muestren el listado y una creación, actualización y eliminación. No incluir contraseñas en capturas ni archivos del repositorio.
