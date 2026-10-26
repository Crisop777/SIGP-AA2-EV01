
package co.sigp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sigp_db";
    private static final String USER =
            System.getenv("SIGP_DB_USER");
    private static final String PASSWORD =
            System.getenv("SIGP_DB_PASSWORD");

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
    if (USER == null || PASSWORD == null) {
        throw new SQLException(
            "Configura las variables SIGP_DB_USER y SIGP_DB_PASSWORD."
        );
    }

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("No se encontró MySQL Connector/J.", e);
    }

    return DriverManager.getConnection(URL, USER, PASSWORD);
}
}
