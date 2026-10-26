# SIGP — Gestión de tareas (GA7-220501096-AA2-EV01)

Módulo web Java para administrar tareas del Sistema de Información y Gestión de Proyectos (SIGP). Usa JDBC con MySQL y ofrece las operaciones para crear, consultar, actualizar y eliminar tareas.

## Tecnologías
- Java 17, Maven, Jakarta Servlet 6.0 y JSP
- Apache Tomcat 10.1, MySQL y MySQL Connector/J 9.7

## Preparar la base de datos
1. Iniciar MySQL.
2. Ejecutar `docs/base_datos.sql` con MySQL Workbench o la consola MySQL.
3. Configurar las variables `SIGP_DB_USER` y `SIGP_DB_PASSWORD` con un usuario que tenga permisos sobre `sigp_db`.
4. Reiniciar la terminal o el IDE para que lea las variables.

La conexión usa `jdbc:mysql://localhost:3306/sigp_db`. Las credenciales no se guardan en el código fuente.

## Compilar y desplegar
Desde la carpeta del proyecto, ejecutar `mvn clean package`. Copiar `target/SIGP.war` a la carpeta `webapps` de Tomcat 10.1, iniciar Tomcat y abrir `http://localhost:8080/SIGP/`.

## Estructura y CRUD
`TareaServlet` procesa las solicitudes, `TareaDAO` ejecuta consultas preparadas JDBC, `Tarea` representa la entidad y `DatabaseConnection` administra la conexión. El módulo permite crear, consultar, actualizar y eliminar tareas.

## Pruebas y repositorio
La guía de pruebas y las capturas están en `docs/Evidencias/`. El enlace al repositorio EV01 está en `URL_REPOSITORIO.txt`.
