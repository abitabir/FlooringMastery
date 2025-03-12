/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.orderDAOTests;

import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.model.Order;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import daoTests.DAOTestSuiteTest;
import daoTests.helpers.OrderTestHelper;

/**
 *
 * @author khali
 */

public class updateOrderTest {
    
    @Test
    public void updateOrderTest() throws SQLException {
        Connection connection = DAOTestSuiteTest.getConnection();
        OrderDAO orderDAO = DAOTestSuiteTest.getOrderDAO();

        Order order = OrderTestHelper.createDefaultOrder();
        Order createdOrder = orderDAO.createOrder(connection, order);

        order.setCustomerName("Jane Doe");
        order.setState("WA");
        order.setTaxRate(new BigDecimal("5.06"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("300"));
        order.setCostPerSquareFoot(new BigDecimal("16.20"));
        order.setLaborCostPerSquareFoot(new BigDecimal("6.00"));
        order.setMaterialCost(new BigDecimal("840.00"));
        order.setLaborCost(new BigDecimal("78.00"));
        order.setTax(new BigDecimal("12.00"));
        order.setTotal(new BigDecimal("6579.00"));

        orderDAO.updateOrder(connection, order);
        Order retrievedOrder = orderDAO.readOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate(), new Order());
        OrderTestHelper.verifyOrdersEqual(order, retrievedOrder);
    }
}
