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
public class readOrderTest {
    
    @Test
    public void readOrderTest() throws SQLException {
        
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
        Order retrievedOrder = orderDAO.readOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate(), new Order());
        
        System.out.println(createdOrder);
        System.out.println(retrievedOrder);
        
        assertNotNull(retrievedOrder);
        assertEquals(createdOrder.getCustomerName(), retrievedOrder.getCustomerName());
        assertEquals(createdOrder.getState(), retrievedOrder.getState());
        assertEquals(0, createdOrder.getTaxRate().compareTo(retrievedOrder.getTaxRate()));
        assertEquals(createdOrder.getProductType(), retrievedOrder.getProductType());
        assertEquals(0, createdOrder.getArea().compareTo(retrievedOrder.getArea()));
        assertEquals(0, createdOrder.getCostPerSquareFoot().compareTo(retrievedOrder.getCostPerSquareFoot()));
        assertEquals(0, createdOrder.getLaborCostPerSquareFoot().compareTo(retrievedOrder.getLaborCostPerSquareFoot()));
        assertEquals(0, createdOrder.getMaterialCost().compareTo(retrievedOrder.getMaterialCost()));
        assertEquals(0, createdOrder.getLaborCost().compareTo(retrievedOrder.getLaborCost()));
        assertEquals(0, createdOrder.getTax().compareTo(retrievedOrder.getTax()));
        assertEquals(0, createdOrder.getTotal().compareTo(retrievedOrder.getTotal()));
        assertEquals(createdOrder.getDate(), retrievedOrder.getDate());

    }
    
}
