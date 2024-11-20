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
public class deleteOrderTest {
    @Test
    public void deleteOrderTest() throws SQLException {
        
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
            orderDAO.deleteOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate());
            Order retrievedOrder = orderDAO.readOrder(connection, createdOrder.getOrderNumber(), createdOrder.getDate(), new Order());
            
            assertNotNull(retrievedOrder);
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
