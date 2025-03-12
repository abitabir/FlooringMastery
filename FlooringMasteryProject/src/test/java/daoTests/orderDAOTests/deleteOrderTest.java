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
import static org.junit.Assert.*;
import daoTests.DAOTestSuiteTest;
import daoTests.helpers.OrderTestHelper;

/**
 *
 * @author khali
 */
public class deleteOrderTest {
    @Test
    public void deleteOrderTest() throws SQLException {
        Connection connection = DAOTestSuiteTest.getConnection();
        OrderDAO orderDAO = DAOTestSuiteTest.getOrderDAO();
        
        Order order = OrderTestHelper.createDefaultOrder();
        Order createdOrder = orderDAO.createOrder(connection, order);
        orderDAO.deleteOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate());
        Order retrievedOrder = orderDAO.readOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate(), new Order());

        assertNull(retrievedOrder.getOrderNumber());
        assertNull(retrievedOrder.getCustomerName());
        assertNull(retrievedOrder.getState());
        assertNull(retrievedOrder.getTaxRate());
        assertNull(retrievedOrder.getProductType());
        assertNull(retrievedOrder.getArea());
        assertNull(retrievedOrder.getCostPerSquareFoot());
        assertNull(retrievedOrder.getLaborCostPerSquareFoot());
        assertNull(retrievedOrder.getMaterialCost());
        assertNull(retrievedOrder.getLaborCost());
        assertNull(retrievedOrder.getTax());
        assertNull(retrievedOrder.getTotal());
        assertNull(retrievedOrder.getDate());
    }
}
