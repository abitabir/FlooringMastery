/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.orderDAOTests;

import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.model.Order;
import daoTests.DAOTestSuiteTest;
import daoTests.helpers.OrderTestHelper;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author khali
 */
public class createOrderTest {
    
    @Test
    public void createOrderTest() throws SQLException {
        Connection connection = DAOTestSuiteTest.getConnection();
        OrderDAO orderDAO = DAOTestSuiteTest.getOrderDAO();

        Order order = OrderTestHelper.createDefaultOrder();
        Order createdOrder = orderDAO.createOrder(connection, order);
        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getOrderNumber());
        OrderTestHelper.verifyOrdersEqual(order, createdOrder);
    }
}
