/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package daoTests;

import com.mthree.flooringmasteryproject.dao.DatabaseSetup;
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
import lombok.Getter;
import org.junit.AfterClass;
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

public class DAOTestSuite {
    private static Connection connection;
    private static Statement statement;

    @BeforeClass
    public static void setUp() {
        DatabaseSetup dbConnection = new DatabaseSetup();
        
        try {
            dbConnection.setConnection(dbConnection.getHost(), 
                                       dbConnection.getPort(), 
                                       dbConnection.getUserName(), 
                                       dbConnection.getPassword());
            connection = dbConnection.getConnection();
            
            dbConnection.createDatabase("flooring");
            dbConnection.createTable("orders");
            dbConnection.createTable("products");
            dbConnection.createTable("taxes");
            statement = dbConnection.getStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static Connection getConnection() {
        return connection;
    }

    @AfterClass
    public static void setDown() {
        if (connection != null) {
            try {
                statement.close();
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
