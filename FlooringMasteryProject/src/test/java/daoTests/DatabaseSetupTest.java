package daoTests;

import com.mthree.flooringmasteryproject.dao.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;

import static org.mockito.Mockito.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author khali
 */

public class DatabaseSetupTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private DatabaseSetup databaseSetup;

    @Before
    public void setUp() throws SQLException {
        System.setProperty("database.name", "flooring_test");
        MockitoAnnotations.openMocks(this);
        databaseSetup = new DatabaseSetup(mockConnection); // Dependency injection
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Simulate no existing data
    }

    @After
    public void resetSystemProperty() {
        // Reset the system property to default
        System.setProperty("database.name", "flooring");
    }

    @Test
    public void testCreateDatabase() throws SQLException {
        // Simulate the behaviour of executing a SQL query successfully
        when(mockStatement.execute(anyString())).thenReturn(true);

        // Call the method being tested
        databaseSetup.createDatabase();

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("CREATE DATABASE IF NOT EXISTS flooring_test");
    }

    @Test
    public void testCreateTableOrders() throws SQLException {        
        // Simulate the behaviour of executing a SQL query successfully
        when(mockStatement.execute(anyString())).thenReturn(true);

        // Call the method being tested
        databaseSetup.createTable("ORDERS");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("CREATE TABLE IF NOT EXISTS ORDERS ("
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
                                        + "Date DATE)");
    }
    
    @Test
    public void testCreateTableProducts() throws SQLException {        
        // Simulate the behaviour of executing a SQL query successfully
        when(mockStatement.execute(anyString())).thenReturn(true);

        // Call the method being tested
        databaseSetup.createTable("PRODUCTS");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("CREATE TABLE IF NOT EXISTS PRODUCTS (" 
                                       + "ProductType VARCHAR(100) PRIMARY KEY, "
                                       + "CostPerSquareFoot DECIMAL(10,2), "
                                       + "LaborCostPerSquareFoot DECIMAL(10,2))");
    }
    
    @Test
    public void testCreateTableTaxes() throws SQLException {        
        // Simulate the behaviour of executing a SQL query successfully
        when(mockStatement.execute(anyString())).thenReturn(true);

        // Call the method being tested
        databaseSetup.createTable("TAXES");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("CREATE TABLE IF NOT EXISTS TAXES ("
                                        + "StateAbbreviation VARCHAR(2) PRIMARY KEY, "
                                        + "StateName VARCHAR(100), "
                                        + "TaxRate DECIMAL(5,2))");
    }

    @Test
    public void testInsertDefaultRecordsOrders() throws SQLException {
        // Simulate the behaviour of executing a SQL query successfully...
        when(mockResultSet.next()).thenReturn(true);
        // ... but where the table is initially empty
        when(mockResultSet.getInt(1)).thenReturn(0);

        // Call the method being tested
        databaseSetup.insertDefaultRecords("ORDERS");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("INSERT INTO ORDERS (OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total, Date) VALUES "
                                        + "(1, 'Ada Lovelace', 'CA', 25.00, 'Tile', 249.00, 3.50, 4.15, 871.50, 1033.35, 476.21, 2381.06, '2025-06-01'), "
                                        + "(2, 'Doctor Who', 'WA', 9.25, 'Wood', 243.00, 5.15, 4.75, 1251.45, 1154.25, 216.51, 2622.21, '2025-06-02'), "
                                        + "(3, 'Albert Einstein', 'KY', 6.00, 'Carpet', 217.00, 2.25, 2.10, 488.25, 455.70, 56.64, 1000.59, '2025-06-02')");
    }

    @Test
    public void testInsertDefaultRecordsProducts() throws SQLException {
        // Simulate the behaviour of executing a SQL query successfully...
        when(mockResultSet.next()).thenReturn(true);
        // ... but where the table is initially empty
        when(mockResultSet.getInt(1)).thenReturn(0);

        // Call the method being tested
        databaseSetup.insertDefaultRecords("PRODUCTS");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("INSERT INTO PRODUCTS (ProductType, CostPerSquareFoot, LaborCostPerSquareFoot) VALUES "
                                        + "('Carpet', 2.25, 2.10), "
                                        + "('Laminate', 1.75, 2.10), "
                                        + "('Tile', 3.50, 4.15), "
                                        + "('Wood', 5.15, 4.75)");
    }

    @Test
    public void testInsertDefaultRecordsTaxes() throws SQLException {
        // Simulate the behaviour of executing a SQL query successfully...
        when(mockResultSet.next()).thenReturn(true);
        // ... but where the table is initially empty
        when(mockResultSet.getInt(1)).thenReturn(0);

        // Call the method being tested
        databaseSetup.insertDefaultRecords("TAXES");

        // Verify that the execute method of mockStatement was called with the following query
        verify(mockStatement).execute("INSERT INTO TAXES (StateAbbreviation, StateName, TaxRate) VALUES "
                                        + "('TX', 'Texas', 4.45), "
                                        + "('WA', 'Washington', 9.25), "
                                        + "('KY', 'Kentucky', 6.00), "
                                        + "('CA', 'California', 25.00)");
    }
}