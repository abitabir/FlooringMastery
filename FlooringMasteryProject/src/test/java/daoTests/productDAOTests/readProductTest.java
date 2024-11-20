/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.productDAOTests;

import com.mthree.flooringmasteryproject.dao.ProductDAO;
import com.mthree.flooringmasteryproject.model.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import static daoTests.DAOTestSuite.getConnection;

/**
 *
 * @author khali
 */
public class readProductTest {

    @Test
    public void readProductTest() throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        Connection connection = getConnection();

        Product product = new Product();
        product.setProductType("Insulation");
        product.setCostPerSquareFoot(new BigDecimal("5.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.00"));

        productDAO.createProduct(connection, product);
        Product retrievedProduct = productDAO.readProduct(connection, product.getProductType(), new Product());

        assertNotNull(retrievedProduct);
        assertEquals(product.getProductType(), retrievedProduct.getProductType());
        assertEquals(0, product.getCostPerSquareFoot().compareTo(retrievedProduct.getCostPerSquareFoot()));
        assertEquals(0, product.getLaborCostPerSquareFoot().compareTo(retrievedProduct.getLaborCostPerSquareFoot()));
    }
    
}
