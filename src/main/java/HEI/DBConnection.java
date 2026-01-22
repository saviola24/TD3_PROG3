package HEI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/new_schema";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fenitra";

    private static Connection instance = null;

    private DBConnection() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                // Charge le driver (optionnel depuis JDBC 4.0 mais parfois utile)
                Class.forName("org.postgresql.Driver");

                instance = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion PostgreSQL établie avec succès.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL non trouvé", e);
            }
        }
        return instance;
    }

    public static void closeConnection() {
        if (instance != null) {
            try {
                if (!instance.isClosed()) {
                    instance.close();
                    System.out.println("Connexion PostgreSQL fermée.");
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
            instance = null;
        }
    }
}
