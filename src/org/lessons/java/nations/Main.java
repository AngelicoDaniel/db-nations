package org.lessons.java.nations;

import java.sql.*;

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
            // preparo lo statement SQL da eseguire
            String sql = "SELECT c.name AS nation_name, c.country_id AS nation_id, r.name AS region_name, co.name AS continent_name\n" +
                    "FROM countries c\n" +
                    "JOIN regions r ON c.region_id = r.region_id\n" +
                    "JOIN continents co ON r.continent_id = co.continent_id\n" +
                    "ORDER BY c.name";
            // chiedo alla Connection di preparare lo statement
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                // eseguo lo statement che restituisce un ResultSet
                try(ResultSet rs = ps.executeQuery()){
                    // itero sulle righe del RestultSet
                    while (rs.next()){
                        // per ogni riga prendo i valori delle singole colonne
                        String nomeNazione = rs.getString("nation_name");
                        int idNazione = rs.getInt("nation_id");
                        String nomeRegione = rs.getString("region_name");
                        String nomeContinente = rs.getString("continent_name");
                        // stampo i valori trovati
                        System.out.println("Nation: " + nomeNazione + " ID: " + idNazione + " Region: " + nomeRegione + " Continent: " + nomeContinente);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }
}
