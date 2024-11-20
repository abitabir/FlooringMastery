/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.orderDAOTests;

import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.model.Order;
import static daoTests.DAOTestSuite.getConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author khali
 */
public class createOrderTest {
    
    @Test
    public void createOrderTest() throws SQLException {
        
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

            assertNotNull(createdOrder);
            assertNotNull(createdOrder.getOrderNumber());
    }
    
}
