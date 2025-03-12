/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.orderDAOTests;

import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.model.Order;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import daoTests.DAOTestSuiteTest;
import daoTests.helpers.OrderTestHelper;

/**
 *
 * @author khali
 */
public class readOrderTest {
    
    @Test
    public void readOrderTest() throws SQLException {
        Connection connection = DAOTestSuiteTest.getConnection();
        OrderDAO orderDAO = DAOTestSuiteTest.getOrderDAO();

        Order order = OrderTestHelper.createDefaultOrder();
        Order createdOrder = orderDAO.createOrder(connection, order);
        Order retrievedOrder = orderDAO.readOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate(), new Order());
        OrderTestHelper.verifyOrdersEqual(createdOrder, retrievedOrder);
    }
    
}
