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
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import static daoTests.DAOTestSuite.getConnection;

/**
 *
 * @author khali
 */

public class updateOrderTest {
    
    @Test
    public void updateOrderTest() throws SQLException {
        
        OrderDAO orderDAO = new OrderDAO();
        Connection connection = getConnection();
        Order order = new Order();
        order.setCustomerName("John Doe");
        order.setState("NY");
        order.setTaxRate(new BigDecimal("0.08"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("100"));
        order.setCostPerSquareFoot(new BigDecimal("5.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("2.00"));
        order.setMaterialCost(new BigDecimal("500.00"));
        order.setLaborCost(new BigDecimal("200.00"));
        order.setTax(new BigDecimal("56.00"));
        order.setTotal(new BigDecimal("756.00"));
        order.setDate(LocalDate.now());

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
        
        System.out.println(order);
        System.out.println(retrievedOrder);
        
        assertNotNull(retrievedOrder);
        // order doesn't have orderNumber property set so don't need to test for it
        assertEquals(order.getCustomerName(), retrievedOrder.getCustomerName());
        assertEquals(order.getState(), retrievedOrder.getState());
        assertEquals(order.getDate(), retrievedOrder.getDate());
        assertEquals(0, order.getTaxRate().compareTo(retrievedOrder.getTaxRate()));
        assertEquals(order.getProductType(), retrievedOrder.getProductType());
        assertEquals(0, order.getArea().compareTo(retrievedOrder.getArea()));
        assertEquals(0, order.getCostPerSquareFoot().compareTo(retrievedOrder.getCostPerSquareFoot()));
        assertEquals(0, order.getLaborCostPerSquareFoot().compareTo(retrievedOrder.getLaborCostPerSquareFoot()));
        assertEquals(0, order.getMaterialCost().compareTo(retrievedOrder.getMaterialCost()));
        assertEquals(0, order.getLaborCost().compareTo(retrievedOrder.getLaborCost()));
        assertEquals(0, order.getTax().compareTo(retrievedOrder.getTax()));
        assertEquals(0, order.getTotal().compareTo(retrievedOrder.getTotal()));
        assertEquals(order.getDate(), retrievedOrder.getDate());
    }
    
}
