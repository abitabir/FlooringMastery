/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mthree.flooringmasteryproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private final Connection connection;
    private static final String DATABASE = System.getProperty("database.name", "flooring");


    // Default constructor (for production)
    public DatabaseSetup() throws SQLException {
        this.connection = MySQLLocalDatabaseConnection.getConnection();
    }

    // Constructor accepting connection (for testing purposes only)
    public DatabaseSetup(Connection connection) {
        this.connection = connection;
    }

    // Create the database if it doesn’t exist
    public void createDatabase() throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DATABASE;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createDatabaseSQL);
            System.out.println("Database ready: " + DATABASE);
        }
    }

    // Create tables if they don't exist
    public void createTable(String tableName) throws SQLException {
        String createTableSQL = switch (tableName.toUpperCase()) {
            case "ORDERS" -> "CREATE TABLE IF NOT EXISTS ORDERS ("
                    + "OrderNumber INT AUTO_INCREMENT PRIMARY KEY, "
                    + "CustomerName VARCHAR(100), "
                    + "State VARCHAR(100), "
                    + "TaxRate DECIMAL(5,2), "
                    + "ProductType VARCHAR(100), "
                    + "Area DECIMAL(10,2), "
                    + "CostPerSquareFoot DECIMAL(10,2), "
                    + "LaborCostPerSquareFoot DECIMAL(10,2), "
                    + "MaterialCost DECIMAL(10,2), "
                    + "LaborCost DECIMAL(10,2), "
                    + "Tax DECIMAL(10,2), "
                    + "Total DECIMAL(10,2), "
                    + "Date DATE)";
            case "PRODUCTS" -> "CREATE TABLE IF NOT EXISTS PRODUCTS ("
                    + "ProductType VARCHAR(100) PRIMARY KEY, "
                    + "CostPerSquareFoot DECIMAL(10,2), "
                    + "LaborCostPerSquareFoot DECIMAL(10,2))";
            case "TAXES" -> "CREATE TABLE IF NOT EXISTS TAXES ("
                    + "StateAbbreviation VARCHAR(2) PRIMARY KEY, "
                    + "StateName VARCHAR(100), "
                    + "TaxRate DECIMAL(5,2))";
            default -> throw new SQLException("Unknown table: " + tableName);
        };

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table ready: " + tableName);
        }
    }
    
    // Create default records if table is empty
    public void insertDefaultRecords(String tableName) throws SQLException {
        String checkIfEmptyQuery = "SELECT COUNT(*) FROM " + tableName;
        int rowCount = 0;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(checkIfEmptyQuery)) {
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        }

        if (rowCount == 0) {
            String insertDataSQL = getDefaultTableRecords(tableName);
            try (Statement statement = connection.createStatement()) {
                statement.execute(insertDataSQL);
                System.out.println("Default records added to " + tableName);
            }
        } else {
            System.out.println(tableName + " already has data. Skipping default inserts.");
        }
    }

    // Returns the default INSERT SQL based on the table
    private String getDefaultTableRecords(String tableName) {
        return switch (tableName.toUpperCase()) {
            case "ORDERS" -> "INSERT INTO ORDERS (OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total, Date) VALUES "
                    + "(1, 'Ada Lovelace', 'CA', 25.00, 'Tile', 249.00, 3.50, 4.15, 871.50, 1033.35, 476.21, 2381.06, '2025-06-01'), "
                    + "(2, 'Doctor Who', 'WA', 9.25, 'Wood', 243.00, 5.15, 4.75, 1251.45, 1154.25, 216.51, 2622.21, '2025-06-02'), "
                    + "(3, 'Albert Einstein', 'KY', 6.00, 'Carpet', 217.00, 2.25, 2.10, 488.25, 455.70, 56.64, 1000.59, '2025-06-02')";
            case "PRODUCTS" -> "INSERT INTO PRODUCTS (ProductType, CostPerSquareFoot, LaborCostPerSquareFoot) VALUES "
                    + "('Carpet', 2.25, 2.10), "
                    + "('Laminate', 1.75, 2.10), "
                    + "('Tile', 3.50, 4.15), "
                    + "('Wood', 5.15, 4.75)";
            case "TAXES" -> "INSERT INTO TAXES (StateAbbreviation, StateName, TaxRate) VALUES "
                    + "('TX', 'Texas', 4.45), "
                    + "('WA', 'Washington', 9.25), "
                    + "('KY', 'Kentucky', 6.00), "
                    + "('CA', 'California', 25.00)";
            default -> throw new IllegalArgumentException("Unknown table: " + tableName);
        };
    }
}
