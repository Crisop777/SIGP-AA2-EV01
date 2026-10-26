# SIGP — Gestión de tareas (GA7-220501096-AA2-EV01)

Módulo web Java para administrar tareas del Sistema de Información y Gestión de Proyectos (SIGP). La aplicación usa JDBC con MySQL y permite crear, consultar, actualizar y eliminar tareas.

## Tecnologías
- Java 17, Maven, Jakarta Servlet 6.0 y JSP
- Apache Tomcat 10.1, MySQL y MySQL Connector/J 9.7

## Preparar la base de datos
1. Iniciar MySQL.
2. Ejecutar `docs/base_datos.sql` con MySQL Workbench o la consola MySQL.
3. Configurar `SIGP_DB_USER` y `SIGP_DB_PASSWORD` con un usuario que tenga permisos sobre `sigp_db`.
4. Reiniciar la terminal o el IDE para que lea las variables.

La conexión usa `jdbc:mysql://localhost:3306/sigp_db`. No se guardan contraseñas en el código fuente.

## Compilar y desplegar
Desde la carpeta del proyecto, ejecutar `mvn clean package`. Copiar `target/SIGP.war` a `webapps` de Tomcat 10.1, iniciar Tomcat y abrir `http://localhost:8080/SIGP/`.

## Funcionalidades CRUD
- Crear una tarea con título, descripción y estado.
- Consultar el listado.
- Actualizar una tarea existente.
- Eliminar una tarea de MySQL.

`TareaServlet` procesa las solicitudes, `TareaDAO` ejecuta consultas preparadas JDBC, `Tarea` representa la entidad y `DatabaseConnection` administra la conexión.

## Pruebas y repositorio
Los pasos de prueba están en `docs/PRUEBAS.md`; guarda capturas reales de ejecución en `evidencias/`. El enlace GitHub de esta evidencia debe anotarse en `URL_REPOSITORIO.txt` después de crear el repositorio EV01, verificando que no sea el de EV02.
