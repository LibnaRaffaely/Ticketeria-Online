package com.project.ticketeria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgres {
    private static final String URL = "jdbc:postgresql://localhost:5432/ticketeria_online";
    private static final String USER = "postgres";
    private static final String PASSWORD = "adm20";

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexao concluída!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver org.postgresql.Driver Nao encontrado");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao BD: " + e.getMessage());
        }

        return connection;
    }
}
