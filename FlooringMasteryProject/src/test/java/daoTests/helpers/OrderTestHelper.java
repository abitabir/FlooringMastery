/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoTests.helpers;

import com.mthree.flooringmasteryproject.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author khali
 */


public class OrderTestHelper {

    public static Order createDefaultOrder() {
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
        return order;
    }

    public static void verifyOrdersEqual(Order expected, Order actual) {
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        assertEquals(expected.getState(), actual.getState());
        assertEquals(0, expected.getTaxRate().compareTo(actual.getTaxRate()));
        assertEquals(expected.getProductType(), actual.getProductType());
        assertEquals(0, expected.getArea().compareTo(actual.getArea()));
        assertEquals(0, expected.getCostPerSquareFoot().compareTo(actual.getCostPerSquareFoot()));
        assertEquals(0, expected.getLaborCostPerSquareFoot().compareTo(actual.getLaborCostPerSquareFoot()));
        assertEquals(0, expected.getMaterialCost().compareTo(actual.getMaterialCost()));
        assertEquals(0, expected.getLaborCost().compareTo(actual.getLaborCost()));
        assertEquals(0, expected.getTax().compareTo(actual.getTax()));
        assertEquals(0, expected.getTotal().compareTo(actual.getTotal()));
        assertEquals(expected.getDate(), actual.getDate());
    }
}
