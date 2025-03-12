package com.mthree.flooringmasteryproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author khali
 */

public class MySQLLocalDatabaseConnection {
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = System.getProperty("database.name", "flooring");
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abir";

    private static Connection connection;

    public static void ensureDatabaseExists() throws SQLException {
        // Connect to MySQL without specifying a database
        try (Connection tempConnection = createConnection("jdbc:mysql://" + HOST + ":" + PORT + "?serverTimezone=UTC", USERNAME, PASSWORD);
             Statement stmt = tempConnection.createStatement()) {

            String checkDbQuery = "SHOW DATABASES LIKE '" + DATABASE + "';";
            ResultSet rs = stmt.executeQuery(checkDbQuery);

            if (!rs.next()) { // If 'flooring' database does not exist, create it
                stmt.executeUpdate("CREATE DATABASE " + DATABASE);
                System.out.println("Database '" + DATABASE + "' created.");
            } else {
                System.out.println("Database '" + DATABASE + "' already exists.");
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?serverTimezone=UTC";
            connection = createConnection(url, USERNAME, PASSWORD);
            System.out.println("Connected to database: " + DATABASE);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    // Method is injectable and can be overridden or mocked in tests
    public static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
