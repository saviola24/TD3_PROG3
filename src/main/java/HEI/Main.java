package HEI;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(
            "jdbc:postgresql://localhost:5432/td3",
            "postgres", 
            "postgres");
        try {
            Connection connection = dbConnection.connect();

            dbConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}