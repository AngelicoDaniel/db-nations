package org.lessons.java.nations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // parametri di connessione
        String url = "jdbc:mysql://localhost:3306/db_nations";
        String user = "root";
        String password = "root";

        // provo ad aprire una connessione al database
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // stampo il catalogo dei database
            System.out.println(connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }
}
