/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package daoTests;

import com.mthree.flooringmasteryproject.dao.DatabaseSetup;
import com.mthree.flooringmasteryproject.dao.MySQLLocalDatabaseConnection;
import com.mthree.flooringmasteryproject.dao.OrderDAO;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import daoTests.orderDAOTests.createOrderTest;
import daoTests.orderDAOTests.readOrderTest;
import daoTests.orderDAOTests.updateOrderTest;
import daoTests.orderDAOTests.deleteOrderTest;
import daoTests.productDAOTests.readProductTest;
import daoTests.taxDAOTests.readTaxTest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;

/**
 *
 * @author khali
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
    createOrderTest.class,
    readOrderTest.class,
    updateOrderTest.class,
    deleteOrderTest.class,
    readProductTest.class,
    readTaxTest.class
})
public class DAOTestSuiteTest {

    // Tests pass when you run DAOTestSuiteTest individually, but not when you build whole project 
    private static Connection connection;
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void setUp() throws SQLException {
        System.setProperty("database.name", "flooring_test");
        
        // Ensure database exists before connecting
        MySQLLocalDatabaseConnection.ensureDatabaseExists();
        connection = MySQLLocalDatabaseConnection.getConnection();
        assertNotNull("Connection should be established", connection);

        // Set up database and tables for tests testing live functionality
        DatabaseSetup dbSetup = new DatabaseSetup(connection);
        dbSetup.createDatabase();
        dbSetup.createTable("ORDERS");
        dbSetup.createTable("PRODUCTS");
        dbSetup.createTable("TAXES");

        // Insert default records
        dbSetup.insertDefaultRecords("ORDERS");
        dbSetup.insertDefaultRecords("PRODUCTS");
        dbSetup.insertDefaultRecords("TAXES");
        
        orderDAO = new OrderDAO();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        // Reset system property to default after testing
        System.setProperty("database.name", "flooring_test");
        
        // Clean up: Drop the test database after the tests
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP DATABASE IF EXISTS flooring_test");
        }
        // Close the connection after tests
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // To provide to tests
    public static Connection getConnection() {
        return connection;
    }

    public static OrderDAO getOrderDAO() {
        return orderDAO;
    }
}