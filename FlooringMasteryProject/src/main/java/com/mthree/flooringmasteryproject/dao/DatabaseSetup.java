/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Getter;
import lombok.Setter;

public class DatabaseSetup {

    @Getter private final String host = "localhost";
    @Getter private final String port = "3306";
    @Getter private final String userName = "root";
    @Getter private final String password = "abir";
    @Getter private Connection connection;
    @Setter @Getter private Statement statement;
    
    public void setConnection(String host, String port, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, userName, password);
        this.connection = con;
        System.out.println("Connection established.");
    }
    
    public void createDatabase(String databaseName) throws SQLException {
        String dropDatabase = "DROP DATABASE IF EXISTS " + databaseName + ";";
        String createDatabase = "CREATE DATABASE " + databaseName + ";";
        String useDatabase = "USE " + databaseName + ";";
        Statement stat = this.getConnection().createStatement();
        
        stat.execute(dropDatabase);
        stat.execute(createDatabase);
        stat.execute(useDatabase);
        this.setStatement(stat);
        System.out.println("Database created.");
    }
        
    public void createTable(String tableName) throws SQLException {

        String dropTable = "DROP TABLE IF EXISTS " + tableName + ";";
        String tableCols = this.createDefaultTableColumns(tableName);
        String createTable = "CREATE TABLE " + tableName + " "
                + tableCols + ";";
        String tableRecs = this.createDefaultTableRecords(tableName);
        String populateTable = "INSERT INTO " + tableName + " VALUES " + tableRecs + ";";
        
        try (Statement tbstatement = this.getConnection().createStatement()) {
            tbstatement.execute(dropTable);
            tbstatement.execute(createTable);
            tbstatement.execute(populateTable);
        }
        
        System.out.println(tableName + " table created.");
    }
        
    public String createDefaultTableColumns(String tableName) throws SQLException {
        String tableCols;
        
        switch (tableName.toUpperCase()) {
            case "ORDERS" -> {
                tableCols = "(" + "OrderNumber INT NOT NULL AUTO_INCREMENT, "
                                + "CustomerName VARCHAR(100), "
                                + "State VARCHAR(100), "
                                + "TaxRate INT, "
                                + "ProductType VARCHAR(100), "
                                + "Area DECIMAL(10, 2), "
                                + "CostPerSquareFoot DECIMAL(10, 2), "
                                + "LaborCostPerSquareFoot DECIMAL(10, 2), "
                                + "MaterialCost DECIMAL(10, 2), "
                                + "LaborCost DECIMAL(10, 2), "
                                + "Tax DECIMAL(10, 2), "
                                + "Total DECIMAL(10, 2), "
                                + "Date DATE, "
                                + "PRIMARY KEY (OrderNumber)" + ")";
            }
            case "PRODUCTS" -> {
                tableCols = "(" + "ProductType VARCHAR(100) NOT NULL, "
                                + "CostPerSquareFoot DECIMAL(10, 2), "
                                + "LaborCostPerSquareFoot DECIMAL(10, 2), "
                                + "PRIMARY KEY (ProductType)" + ")";
            }
            case "TAXES" -> {
                tableCols = "(" + "StateAbbreviation VARCHAR(100) NOT NULL, "
                                + "StateName VARCHAR(100), "
                                + "TaxRate DECIMAL(10, 2), "
                                + "PRIMARY KEY (StateAbbreviation)" + ")";
            }
            default -> {
                tableCols = "(" + "id INT NOT NULL AUTO_INCREMENT, "
                                + "name VARCHAR(100), "
                                + "age INT, "
                                + "PRIMARY KEY (id)" + ")";
            }
        }
        return tableCols;
    }
    
    public String createDefaultTableRecords(String tableName) {
        String tableRecs;
        
        switch (tableName.toUpperCase()) {
            case "ORDERS" -> {
                tableRecs = "(" + "1, 'Ada Lovelace', 'CA', 25.00, 'Tile', 249.00, 3.50, 4.15, 871.50, 1033.35, 476.21, 2381.06, '2025-06-01'" + "), "
                          + "(" + "2, 'Doctor Who', 'WA', 9.25, 'Wood', 243.00, 5.15, 4.75, 1251.45, 1154.25, 216.51, 2622.21, '2025-06-02'" + "), "
                          + "(" + "3, 'Albert Einstein', 'KY', 6.00, 'Carpet', 217.00, 2.25, 2.10, 488.25, 455.70, 56.64, 1000.59, '2025-06-02'" + ")";
            }
            case "PRODUCTS" -> {
                tableRecs = "(" + "'Carpet', 2.25, 2.10" + "), "
                          + "(" + "'Laminate', 1.75, 2.10" + "), "
                          + "(" + "'Tile', 3.50, 4.15" + "), "
                          + "(" + "'Wood', 5.15, 4.75" + ")";
            }
            case "TAXES" -> {
                tableRecs = "(" + "'TX', 'Texas', 4.45" + "), "
                          + "(" + "'WA', 'Washington', 9.25" + "), "
                          + "(" + "'KY', 'Kentucky', 6.00" + "), "
                          + "(" + "'CA', 'Calfornia', 25.00" + ")";
            }
            default -> {
                tableRecs = "(" + "1, 'Aname Asurname', 7" + "), "
                          + "(" + "2, 'Aname Asurname', 8" + "), "
                          + "(" + "3, 'Aname Asurname', 8" + "), "
                          + "(" + "4, 'Aname Asurname', 7" + ")";
            }
        }
        return tableRecs;
    }

}
	 
