package HEI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DBConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws Exception {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connection a la base de donnee reussi");
            } catch (Exception e) {
                System.err.println("Erreur de connexion a la base de donnee: " + e.getMessage());
                throw e;
            }
        }
        return connection;
        
    }

    public void disconnect(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Deconnexion de la base de donnee reussi");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la deconnexion de la base de donnee: " + e.getMessage());
        }
    }
}
